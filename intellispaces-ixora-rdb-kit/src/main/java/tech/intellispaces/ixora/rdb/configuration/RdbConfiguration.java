package tech.intellispaces.ixora.rdb.configuration;

import tech.intellispaces.ixora.rdb.datasource.DataSourceSettings;
import tech.intellispaces.ixora.rdb.datasource.MovableDataSource;
import tech.intellispaces.ixora.rdb.query.CastStringToParameterizedNamedQueryGuideImpl;
import tech.intellispaces.ixora.rdb.statement.ResultSetToDataGuideImpl;
import tech.intellispaces.ixora.rdb.transaction.MovableTransactionFactory;
import tech.intellispaces.ixora.rdb.transaction.MovableTransactionFactoryHandle;
import tech.intellispaces.ixora.rdb.transaction.TransactionFactoryOverDataSourceWrapper;
import tech.intellispaces.jaquarius.annotation.Configuration;
import tech.intellispaces.jaquarius.annotation.Projection;
import tech.intellispaces.jaquarius.annotation.Properties;

@Configuration({
    CastStringToParameterizedNamedQueryGuideImpl.class,
    ResultSetToDataGuideImpl.class
})
public abstract class RdbConfiguration {

  /**
   * Data source properties.
   */
  @Projection
  @Properties("datasource")
  public abstract DataSourceSettings dataSourceSettings();

  /**
   * Transaction factory.
   */
  @Projection
  public MovableTransactionFactoryHandle transactionFactory(MovableDataSource dataSource) {
    return new TransactionFactoryOverDataSourceWrapper(dataSource);
  }
}
