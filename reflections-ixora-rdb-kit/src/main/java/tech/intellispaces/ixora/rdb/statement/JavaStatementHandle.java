package tech.intellispaces.ixora.rdb.statement;

import java.sql.SQLException;
import java.sql.Statement;

import tech.intellispaces.ixora.rdb.exception.RdbExceptions;
import tech.intellispaces.reflections.annotation.Mapper;
import tech.intellispaces.reflections.annotation.ObjectHandle;

@ObjectHandle(StatementDomain.class)
abstract class JavaStatementHandle implements MovableStatement {
  private final Statement statement;

  JavaStatementHandle(Statement statement) {
    this.statement = statement;
  }

  @Mapper
  @Override
  public MovableResultSet executeQuery(String query) {
    try {
      java.sql.ResultSet rs = statement.executeQuery(query);
      return new JavaResultSetHandleWrapper(rs);
    } catch (SQLException e) {
      throw RdbExceptions.withCauseAndMessage(e, "Could not execute query {0}", query);
    }
  }
}
