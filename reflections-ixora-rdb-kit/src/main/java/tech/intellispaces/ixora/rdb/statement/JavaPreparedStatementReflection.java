package tech.intellispaces.ixora.rdb.statement;

import java.sql.SQLException;

import tech.intellispaces.ixora.rdb.exception.RdbExceptions;
import tech.intellispaces.reflections.framework.annotation.Mapper;
import tech.intellispaces.reflections.framework.annotation.Mover;
import tech.intellispaces.reflections.framework.annotation.Reflection;

@Reflection(domainClass = PreparedStatementDomain.class)
abstract class JavaPreparedStatementReflection implements MovablePreparedStatement {
  private final java.sql.PreparedStatement preparedStatement;

  JavaPreparedStatementReflection(java.sql.PreparedStatement preparedStatement) {
    this.preparedStatement = preparedStatement;
  }

  @Mapper
  @Override
  public MovableResultSet executeQuery() {
    try {
      java.sql.ResultSet rs = preparedStatement.executeQuery();
      return new JavaResultSetReflectionWrapper(rs);
    } catch (SQLException e) {
      throw RdbExceptions.withCauseAndMessage(e, "Could not execute prepared statement");
    }
  }

  @Mover
  @Override
  public MovablePreparedStatement setInt(int parameterIndex, int value) {
    try {
      preparedStatement.setInt(parameterIndex, value);
      return this;
    } catch (SQLException e) {
      throw RdbExceptions.withCauseAndMessage(e, "Could not set parameter of the prepared statement");
    }
  }
}
