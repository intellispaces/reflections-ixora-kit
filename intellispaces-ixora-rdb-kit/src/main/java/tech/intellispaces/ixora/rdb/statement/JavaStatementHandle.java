package tech.intellispaces.ixora.rdb.statement;

import tech.intellispaces.jaquarius.annotation.Mapper;
import tech.intellispaces.jaquarius.annotation.ObjectHandle;
import tech.intellispaces.ixora.rdb.exception.RdbExceptions;

import java.sql.SQLException;
import java.sql.Statement;

@ObjectHandle(StatementDomain.class)
abstract class JavaStatementHandle implements MovableStatementHandle {
  private final Statement statement;

  JavaStatementHandle(Statement statement) {
    this.statement = statement;
  }

  @Mapper
  @Override
  public MovableResultSetHandle executeQuery(String query) {
    try {
      java.sql.ResultSet rs = statement.executeQuery(query);
      return new JavaResultSetHandleWrapper(rs);
    } catch (SQLException e) {
      throw RdbExceptions.withCauseAndMessage(e, "Could not execute query {0}", query);
    }
  }
}
