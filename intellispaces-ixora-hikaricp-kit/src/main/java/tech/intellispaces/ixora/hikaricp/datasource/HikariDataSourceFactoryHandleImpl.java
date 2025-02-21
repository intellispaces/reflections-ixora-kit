package tech.intellispaces.ixora.hikaricp.datasource;

import com.zaxxer.hikari.HikariConfig;
import tech.intellispaces.jaquarius.annotation.MapperOfMoving;
import tech.intellispaces.jaquarius.annotation.ObjectHandle;
import tech.intellispaces.ixora.rdb.hikaricp.datasource.HikariDataSourceFactoryDomain;
import tech.intellispaces.ixora.rdb.hikaricp.datasource.HikariDataSourceSettingsHandle;
import tech.intellispaces.ixora.rdb.hikaricp.datasource.MovableHikariDataSourceFactoryHandle;
import tech.intellispaces.ixora.rdb.hikaricp.datasource.MovableHikariDataSourceHandle;

@ObjectHandle(HikariDataSourceFactoryDomain.class)
public abstract class HikariDataSourceFactoryHandleImpl implements MovableHikariDataSourceFactoryHandle {

  @Override
  @MapperOfMoving
  public MovableHikariDataSourceHandle create(HikariDataSourceSettingsHandle settings) {
    var config = new HikariConfig();
    config.setJdbcUrl(settings.url().trim());
    config.setUsername(settings.username().trim());
    config.setPassword(settings.password().trim());
    var hds = new com.zaxxer.hikari.HikariDataSource(config);
    return new HikariDataSourceHandleImplWrapper(hds, settings);
  }
}
