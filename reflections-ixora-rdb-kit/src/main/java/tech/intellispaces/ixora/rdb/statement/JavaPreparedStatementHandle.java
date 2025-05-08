package tech.intellispaces.ixora.rdb.statement;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import tech.intellispaces.ixora.rdb.exception.RdbExceptions;
import tech.intellispaces.reflections.annotation.Mapper;
import tech.intellispaces.reflections.annotation.Mover;
import tech.intellispaces.reflections.annotation.ObjectHandle;

@ObjectHandle(PreparedStatementDomain.class)
abstract class JavaPreparedStatementHandle implements MovablePreparedStatement {
  private final PreparedStatement preparedStatement;

  JavaPreparedStatementHandle(PreparedStatement preparedStatement) {
    this.preparedStatement = preparedStatement;
  }

  @Mapper
  @Override
  public MovableResultSet executeQuery() {
    try {
      java.sql.ResultSet rs = preparedStatement.executeQuery();
      return new JavaResultSetHandleWrapper(rs);
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
