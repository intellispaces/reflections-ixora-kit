package tech.intellispaces.ixora.hikaricp.datasource;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tech.intellispaces.ixora.rdb.datasource.JavaConnectionReflectionWrapper;
import tech.intellispaces.ixora.rdb.datasource.MovableConnection;
import tech.intellispaces.ixora.rdb.hikaricp.datasource.HikariDataSourceDomain;
import tech.intellispaces.ixora.rdb.hikaricp.datasource.HikariDataSourceSettings;
import tech.intellispaces.ixora.rdb.hikaricp.datasource.MovableHikariDataSource;
import tech.intellispaces.reflections.framework.annotation.Mapper;
import tech.intellispaces.reflections.framework.annotation.MapperOfMoving;
import tech.intellispaces.reflections.framework.annotation.Reflection;
import tech.intellispaces.reflections.framework.exception.TraverseExceptions;

@Reflection(domainClass = HikariDataSourceDomain.class)
public abstract class HikariDataSourceReflection implements MovableHikariDataSource {
  private static final Logger LOG = LoggerFactory.getLogger(HikariDataSourceReflection.class);

  private final HikariDataSourceSettings dataSourceProperties;
  private final com.zaxxer.hikari.HikariDataSource dataSource;

  public HikariDataSourceReflection(
      com.zaxxer.hikari.HikariDataSource dataSource,
      HikariDataSourceSettings dataSourceSettings
  ) {
    this.dataSource = dataSource;
    this.dataSourceProperties = dataSourceSettings;
  }

  @Mapper
  @Override
  public HikariDataSourceSettings settings() {
    return dataSourceProperties;
  }

  @Override
  @MapperOfMoving
  public MovableConnection getConnection() {
    if (LOG.isDebugEnabled()) {
      LOG.debug("Get JDBC connection from Hikari data source. URL '{}', username '{}'", url(), username());
    }
    try {
      java.sql.Connection connection = dataSource.getConnection();
      return new JavaConnectionReflectionWrapper(connection);
    } catch (SQLException e) {
      throw TraverseExceptions.withCauseAndMessage(e, "Could not get JDBC connection from Hikari data source. " +
          "URL '{}', username '{}'");
    }
  }

  private String url() {
    return dataSourceProperties.url().trim();
  }

  private String username() {
    return dataSourceProperties.username().trim();
  }
}
