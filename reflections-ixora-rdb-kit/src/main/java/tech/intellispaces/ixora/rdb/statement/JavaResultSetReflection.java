package tech.intellispaces.ixora.rdb.statement;

import java.sql.SQLException;

import tech.intellispaces.reflections.framework.annotation.Mapper;
import tech.intellispaces.reflections.framework.annotation.MapperOfMoving;
import tech.intellispaces.reflections.framework.annotation.Reflection;
import tech.intellispaces.reflections.framework.exception.TraverseExceptions;

@Reflection(domainClass = ResultSetDomain.class)
abstract class JavaResultSetReflection implements MovableResultSet {
  private final java.sql.ResultSet rs;

  JavaResultSetReflection(java.sql.ResultSet rs) {
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
