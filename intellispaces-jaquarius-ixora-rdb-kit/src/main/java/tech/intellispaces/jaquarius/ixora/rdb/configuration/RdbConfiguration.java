package tech.intellispaces.jaquarius.ixora.rdb.configuration;

import tech.intellispaces.jaquarius.annotation.Configuration;
import tech.intellispaces.jaquarius.annotation.Projection;
import tech.intellispaces.jaquarius.annotation.Settings;
import tech.intellispaces.jaquarius.ixora.rdb.datasource.DataSourceSettingsHandle;
import tech.intellispaces.jaquarius.ixora.rdb.datasource.MovableDataSourceHandle;
import tech.intellispaces.jaquarius.ixora.rdb.query.StringToParameterizedNamedQueryGuideImpl;
import tech.intellispaces.jaquarius.ixora.rdb.statement.ResultSetToDataGuideImpl;
import tech.intellispaces.jaquarius.ixora.rdb.transaction.MovableTransactionFactoryHandle;
import tech.intellispaces.jaquarius.ixora.rdb.transaction.TransactionFactoryHandleOverDataSourceWrapper;

@Configuration({
    StringToParameterizedNamedQueryGuideImpl.class,
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
