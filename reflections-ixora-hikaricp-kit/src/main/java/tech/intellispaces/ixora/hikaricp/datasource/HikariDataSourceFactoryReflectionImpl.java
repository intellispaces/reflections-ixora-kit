package tech.intellispaces.ixora.hikaricp.datasource;

import com.zaxxer.hikari.HikariConfig;
import tech.intellispaces.ixora.rdb.hikaricp.datasource.HikariDataSourceFactoryDomain;
import tech.intellispaces.ixora.rdb.hikaricp.datasource.HikariDataSourceSettings;
import tech.intellispaces.ixora.rdb.hikaricp.datasource.MovableHikariDataSource;
import tech.intellispaces.ixora.rdb.hikaricp.datasource.MovableHikariDataSourceFactory;
import tech.intellispaces.reflections.framework.annotation.MapperOfMoving;
import tech.intellispaces.reflections.framework.annotation.Reflection;

@Reflection(HikariDataSourceFactoryDomain.class)
public abstract class HikariDataSourceFactoryReflectionImpl implements MovableHikariDataSourceFactory {

  @Override
  @MapperOfMoving
  public MovableHikariDataSource create(HikariDataSourceSettings settings) {
    var config = new HikariConfig();
    config.setJdbcUrl(settings.url().trim());
    config.setUsername(settings.username().trim());
    config.setPassword(settings.password().trim());
    var hds = new com.zaxxer.hikari.HikariDataSource(config);
    return new HikariDataSourceReflectionImplWrapper(hds, settings);
  }
}
