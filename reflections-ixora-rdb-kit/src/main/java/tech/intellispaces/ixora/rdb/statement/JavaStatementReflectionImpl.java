package tech.intellispaces.ixora.rdb.statement;

import java.sql.SQLException;
import java.sql.Statement;

import tech.intellispaces.ixora.rdb.exception.RdbExceptions;
import tech.intellispaces.reflections.framework.annotation.Mapper;
import tech.intellispaces.reflections.framework.annotation.Reflection;

@Reflection(StatementDomain.class)
abstract class JavaStatementReflectionImpl implements MovableStatement {
  private final Statement statement;

  JavaStatementReflectionImpl(Statement statement) {
    this.statement = statement;
  }

  @Mapper
  @Override
  public MovableResultSet executeQuery(String query) {
    try {
      java.sql.ResultSet rs = statement.executeQuery(query);
      return new JavaResultSetReflectionImplWrapper(rs);
    } catch (SQLException e) {
      throw RdbExceptions.withCauseAndMessage(e, "Could not execute query {0}", query);
    }
  }
}
