package tech.intellispaces.ixora.rdb.statement;

import tech.intellispaces.ixora.rdb.exception.RdbExceptions;
import tech.intellispaces.reflections.framework.annotation.Mapper;
import tech.intellispaces.reflections.framework.annotation.Reflection;

import java.sql.Statement;
import java.sql.SQLException;

@Reflection(StatementDomain.class)
abstract class JavaStatementReflection implements MovableStatement {
  private final Statement statement;

  JavaStatementReflection(Statement statement) {
    this.statement = statement;
  }

  @Mapper
  @Override
  public MovableResultSet executeQuery(String query) {
    try {
      java.sql.ResultSet rs = statement.executeQuery(query);
      return new JavaResultSetReflectionWrapper(rs);
    } catch (SQLException e) {
      throw RdbExceptions.withCauseAndMessage(e, "Could not execute query {0}", query);
    }
  }
}
