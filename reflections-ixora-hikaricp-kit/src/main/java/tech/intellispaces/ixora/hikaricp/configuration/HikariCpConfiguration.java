package tech.intellispaces.ixora.hikaricp.configuration;

import tech.intellispaces.ixora.hikaricp.datasource.HikariDataSourceFactoryHandleWrapper;
import tech.intellispaces.ixora.rdb.hikaricp.datasource.HikariDataSourceFactory;
import tech.intellispaces.ixora.rdb.hikaricp.datasource.HikariDataSourceSettings;
import tech.intellispaces.ixora.rdb.hikaricp.datasource.MovableHikariDataSource;
import tech.intellispaces.ixora.rdb.hikaricp.datasource.MovableHikariDataSourceFactory;
import tech.intellispaces.reflections.framework.annotation.Configuration;
import tech.intellispaces.reflections.framework.annotation.Projection;
import tech.intellispaces.reflections.framework.annotation.Properties;

@Configuration
public abstract class HikariCpConfiguration {

  @Projection
  @Properties("datasource")
  public abstract HikariDataSourceSettings hikariDataSourceSettings();

  @Projection
  public HikariDataSourceFactory hikariDataSourceFactory() {
    return new HikariDataSourceFactoryHandleWrapper();
  }

  @Projection
  public MovableHikariDataSource dataSource(
      HikariDataSourceFactory hikariDataSourceFactory,
      HikariDataSourceSettings hikariDataSourceSettings
  ) {
    return ((MovableHikariDataSourceFactory) hikariDataSourceFactory).create(hikariDataSourceSettings);
  }
}
