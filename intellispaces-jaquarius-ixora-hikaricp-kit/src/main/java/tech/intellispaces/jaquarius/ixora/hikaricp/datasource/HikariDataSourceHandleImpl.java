package tech.intellispaces.jaquarius.ixora.hikaricp.datasource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tech.intellispaces.jaquarius.annotation.Mapper;
import tech.intellispaces.jaquarius.annotation.MapperOfMoving;
import tech.intellispaces.jaquarius.annotation.ObjectHandle;
import tech.intellispaces.jaquarius.exception.TraverseExceptions;
import tech.intellispaces.jaquarius.ixora.rdb.datasource.JavaConnectionHandleWrapper;
import tech.intellispaces.jaquarius.ixora.rdb.datasource.MovableConnectionHandle;
import tech.intellispaces.jaquarius.ixora.rdb.hikaricp.datasource.HikariDataSourceDomain;
import tech.intellispaces.jaquarius.ixora.rdb.hikaricp.datasource.HikariDataSourceSettingsHandle;
import tech.intellispaces.jaquarius.ixora.rdb.hikaricp.datasource.MovableHikariDataSourceHandle;

import java.sql.SQLException;

@ObjectHandle(value = HikariDataSourceDomain.class, name = "HikariDataSourceHandleImplWrapper")
public abstract class HikariDataSourceHandleImpl implements MovableHikariDataSourceHandle {
  private static final Logger LOG = LoggerFactory.getLogger(HikariDataSourceHandleImpl.class);

  private final HikariDataSourceSettingsHandle dataSourceProperties;
  private final com.zaxxer.hikari.HikariDataSource dataSource;

  public HikariDataSourceHandleImpl(
      com.zaxxer.hikari.HikariDataSource dataSource,
      HikariDataSourceSettingsHandle dataSourceSettings
  ) {
    this.dataSource = dataSource;
    this.dataSourceProperties = dataSourceSettings;
  }

  @Mapper
  @Override
  public HikariDataSourceSettingsHandle settings() {
    return dataSourceProperties;
  }

  @Override
  @MapperOfMoving
  public MovableConnectionHandle getConnection() {
    if (LOG.isDebugEnabled()) {
      LOG.debug("Get JDBC connection from Hikari data source. URL '{}', username '{}'", url(), username());
    }
    try {
      java.sql.Connection connection = dataSource.getConnection();
      return new JavaConnectionHandleWrapper(connection);
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
