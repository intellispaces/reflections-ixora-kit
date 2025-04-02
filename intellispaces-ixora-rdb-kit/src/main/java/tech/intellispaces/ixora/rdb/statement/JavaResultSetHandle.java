package tech.intellispaces.ixora.rdb.statement;

import tech.intellispaces.jaquarius.annotation.Mapper;
import tech.intellispaces.jaquarius.annotation.MapperOfMoving;
import tech.intellispaces.jaquarius.annotation.ObjectHandle;
import tech.intellispaces.jaquarius.exception.TraverseExceptions;

import java.sql.ResultSet;
import java.sql.SQLException;

@ObjectHandle(ResultSetDomain.class)
abstract class JavaResultSetHandle implements MovableResultSet {
  private final ResultSet rs;

  JavaResultSetHandle(ResultSet rs) {
    this.rs = rs;
  }

  @Override
  @MapperOfMoving
  public boolean next() {
    try {
      return rs.next();
    } catch (SQLException e) {
      throw TraverseExceptions.withCauseAndMessage(e, "Could not move cursor");
    }
  }

  @Mapper
  @Override
  public int integer32Value(String name) {
    try {
      int value = rs.getInt(name);
      if (rs.wasNull()) {
        throw TraverseExceptions.withMessage("Could not read integer primitive value by name '{0}'. Value is null", name);
      }
      return value;
    } catch (SQLException e) {
      throw TraverseExceptions.withCauseAndMessage(e, "Could not read integer value by name '{0}'", name);
    }
  }

  @Mapper
  @Override
  public String stringValue(String name) {
    try {
      String value = rs.getString(name);
      if (rs.wasNull()) {
        return null;
      }
      return value;
    } catch (SQLException e) {
      throw TraverseExceptions.withCauseAndMessage(e, "Could not read string value by name {0}", name);
    }
  }
}
