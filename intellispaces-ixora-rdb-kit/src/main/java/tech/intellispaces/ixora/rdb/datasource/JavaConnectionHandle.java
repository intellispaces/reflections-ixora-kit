package tech.intellispaces.ixora.rdb.datasource;

import java.sql.SQLException;

import tech.intellispaces.ixora.rdb.exception.RdbExceptions;
import tech.intellispaces.ixora.rdb.statement.JavaPreparedStatementHandleWrapper;
import tech.intellispaces.ixora.rdb.statement.JavaStatementHandleWrapper;
import tech.intellispaces.ixora.rdb.statement.MovablePreparedStatement;
import tech.intellispaces.ixora.rdb.statement.MovableStatement;
import tech.intellispaces.jaquarius.annotation.MapperOfMoving;
import tech.intellispaces.jaquarius.annotation.Mover;
import tech.intellispaces.jaquarius.annotation.ObjectHandle;

@ObjectHandle(ConnectionDomain.class)
abstract class JavaConnectionHandle implements MovableConnection {
  private final java.sql.Connection connection;

  JavaConnectionHandle(java.sql.Connection connection) {
    this.connection = connection;
  }

  @Override
  @MapperOfMoving
  public MovableStatement createStatement() {
    try {
      return new JavaStatementHandleWrapper(connection.createStatement());
    } catch (SQLException e) {
      throw RdbExceptions.withCauseAndMessage(e, "Could not create statement");
    }
  }

  @Override
  @MapperOfMoving
  public MovablePreparedStatement createPreparedStatement(String query) {
    try {
      return new JavaPreparedStatementHandleWrapper(connection.prepareStatement(query));
    } catch (SQLException e) {
      throw RdbExceptions.withCauseAndMessage(e, "Could not create statement");
    }
  }

  @Mover
  @Override
  public MovableConnection disableAutoCommit() {
    try {
      connection.setAutoCommit(false);
    } catch (SQLException e) {
      throw RdbExceptions.withCauseAndMessage(e, "Could not disable SQL connection auto commit");
    }
    return this;
  }

  @Mover
  @Override
  public MovableConnection commit() {
    try {
      connection.commit();
    } catch (SQLException e) {
      throw RdbExceptions.withCauseAndMessage(e, "Could not commit SQL connection");
    }
    return this;
  }

  @Mover
  @Override
  public MovableConnection rollback() {
    try {
      connection.rollback();
    } catch (SQLException e) {
      throw RdbExceptions.withCauseAndMessage(e, "Could not roll back SQL connection");
    }
    return this;
  }

  @Mover
  @Override
  public MovableConnection close() {
    try {
      connection.close();
    } catch (SQLException e) {
      throw RdbExceptions.withCauseAndMessage(e, "Could not close SQL connection");
    }
    return this;
  }
}
