package tech.intellispaces.ixora.rdb.configuration;

import tech.intellispaces.jaquarius.annotation.Configuration;
import tech.intellispaces.jaquarius.annotation.Projection;
import tech.intellispaces.jaquarius.annotation.Settings;
import tech.intellispaces.ixora.rdb.datasource.DataSourceSettingsHandle;
import tech.intellispaces.ixora.rdb.datasource.MovableDataSourceHandle;
import tech.intellispaces.ixora.rdb.query.CastStringToParameterizedNamedQueryGuideImpl;
import tech.intellispaces.ixora.rdb.statement.ResultSetToDataGuideImpl;
import tech.intellispaces.ixora.rdb.transaction.MovableTransactionFactoryHandle;
import tech.intellispaces.ixora.rdb.transaction.TransactionFactoryHandleOverDataSourceWrapper;

@Configuration({
    CastStringToParameterizedNamedQueryGuideImpl.class,
    ResultSetToDataGuideImpl.class
})
public abstract class RdbConfiguration {

  /**
   * Data source properties.
   */
  @Projection
  @Settings("datasource")
  public abstract DataSourceSettingsHandle dataSourceSettings();

  /**
   * Transaction factory.
   */
  @Projection
  public MovableTransactionFactoryHandle transactionFactory(MovableDataSourceHandle dataSource) {
    return new TransactionFactoryHandleOverDataSourceWrapper(dataSource);
  }
}
