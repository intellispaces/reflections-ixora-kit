package tech.intellispaces.ixora.rdb.transaction;

import tech.intellispaces.commons.type.Type;
import tech.intellispaces.ixora.data.association.Map;
import tech.intellispaces.ixora.data.association.MapHandle;
import tech.intellispaces.ixora.data.collection.ListHandle;
import tech.intellispaces.ixora.data.cursor.MovableCursor;
import tech.intellispaces.ixora.rdb.datasource.Connection;
import tech.intellispaces.ixora.rdb.datasource.MovableConnection;
import tech.intellispaces.ixora.rdb.exception.RdbExceptions;
import tech.intellispaces.ixora.rdb.query.CastStringToParameterizedNamedQueryGuide;
import tech.intellispaces.ixora.rdb.query.ParameterizedNamedQuery;
import tech.intellispaces.ixora.rdb.statement.MovablePreparedStatement;
import tech.intellispaces.ixora.rdb.statement.MovableResultSet;
import tech.intellispaces.jaquarius.annotation.AutoGuide;
import tech.intellispaces.jaquarius.annotation.Inject;
import tech.intellispaces.jaquarius.annotation.Mapper;
import tech.intellispaces.jaquarius.annotation.Mover;
import tech.intellispaces.jaquarius.annotation.ObjectHandle;

import static tech.intellispaces.ixora.data.association.Maps.mapHandle;
import static tech.intellispaces.ixora.data.collection.Lists.listHandle;

@ObjectHandle(TransactionDomain.class)
abstract class TransactionOverConnection implements MovableTransaction {
  private final MovableConnection connection;

  @Inject
  @AutoGuide
  abstract CastStringToParameterizedNamedQueryGuide castStringToParameterizedNamedQueryGuide();

  TransactionOverConnection(MovableConnection connection) {
    this.connection = connection;
    connection.disableAutoCommit();
  }

  @Mapper
  @Override
  public Connection connection() {
    return connection;
  }

  @Mover
  @Override
  public MovableTransaction commit() {
    connection.commit();
    connection.close();
    return this;
  }

  @Mover
  @Override
  public MovableTransaction rollback() {
    connection.rollback();
    connection.close();
    return this;
  }

  @Mover
  @Override
  public MovableTransaction modify(String query) {
    throw new RuntimeException("Not implemented");
  }

  @Mapper
  @Override
  public MovableResultSet query(String query) {
    return connection.createStatement().executeQuery(query);
  }

  @Mapper
  @Override
  public <D> MovableCursor<D> queryData(Type<D> dataType, String query) {
    throw new RuntimeException("Not implemented");
  }

  @Mapper
  @Override
  public <D> D fetchData(Type<D> dataType, String query) {
    MovableResultSet rs = connection.createStatement().executeQuery(query);
    return fetchData(dataType, rs);
  }

  @Mapper
  @Override
  public <D> D fetchData(Type<D> dataType, String query, Map<String, Object> params) {
    ParameterizedNamedQuery parameterizedQuery = (
        castStringToParameterizedNamedQueryGuide().castStringToParameterizedNamedQuery(query)
    );
    MovablePreparedStatement ps = connection.createPreparedStatement(parameterizedQuery.query());
    setParamValues(
        ps,
        listHandle(parameterizedQuery.paramNames(), String.class),
        mapHandle(params, String.class, Object.class)
    );
    MovableResultSet rs = ps.executeQuery();
    return fetchData(dataType, rs);
  }

   private <D> D fetchData(Type<D> dataType, MovableResultSet rs) {
    if (!rs.next()) {
      throw RdbExceptions.withMessage("No data found");
    }
    D data = rs.dataValue(dataType);
    if (rs.next()) {
      throw RdbExceptions.withMessage("More than one data was found");
    }
    return data;
  }

  private void setParamValues(
      MovablePreparedStatement ps, ListHandle<String> paramNames, MapHandle<String, Object> params
  ) {
    int index = 1;
    for (Object paramName : paramNames.nativeList()) {
      if (!params.nativeMap().containsKey(paramName)) {
        throw RdbExceptions.withMessage("Value of parameter {0} is not found", paramName);
      }
      Object paramValue = params.nativeMap().get(paramName);
      if (paramValue instanceof Integer) {
        ps.setInt(index++, (int) paramValue);
      } else {
        throw new RuntimeException("Not implemented");
      }
    }
  }
}
