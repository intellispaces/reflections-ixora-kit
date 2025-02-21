package tech.intellispaces.ixora.hikaricp.configuration;

import tech.intellispaces.jaquarius.annotation.Configuration;
import tech.intellispaces.jaquarius.annotation.Projection;
import tech.intellispaces.jaquarius.annotation.Settings;
import tech.intellispaces.ixora.hikaricp.datasource.HikariDataSourceFactoryHandleImplWrapper;
import tech.intellispaces.ixora.rdb.hikaricp.datasource.HikariDataSourceFactoryHandle;
import tech.intellispaces.ixora.rdb.hikaricp.datasource.HikariDataSourceSettingsHandle;
import tech.intellispaces.ixora.rdb.hikaricp.datasource.MovableHikariDataSourceHandle;

@Configuration
public abstract class HikariCpConfiguration {

  @Projection
  @Settings("datasource")
  public abstract HikariDataSourceSettingsHandle hikariDataSourceSettings();

  @Projection
  public HikariDataSourceFactoryHandle hikariDataSourceFactory() {
    return new HikariDataSourceFactoryHandleImplWrapper();
  }

  @Projection
  public MovableHikariDataSourceHandle dataSource(
      HikariDataSourceFactoryHandle hikariDataSourceFactory,
      HikariDataSourceSettingsHandle hikariDataSourceSettings
  ) {
    return hikariDataSourceFactory
        .asMovableOrElseThrow()
        .create(hikariDataSourceSettings)
        .asMovableOrElseThrow();
  }
}
