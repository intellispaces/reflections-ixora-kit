package tech.intellispaces.ixora.rdb.configuration;

import tech.intellispaces.ixora.rdb.datasource.DataSourceSettings;
import tech.intellispaces.ixora.rdb.datasource.MovableDataSource;
import tech.intellispaces.ixora.rdb.query.CastStringToParameterizedNamedQueryGuideImpl;
import tech.intellispaces.ixora.rdb.statement.ResultSetToDataGuideImpl;
import tech.intellispaces.ixora.rdb.transaction.MovableTransactionFactoryReflection;
import tech.intellispaces.ixora.rdb.transaction.TransactionFactoryOverDataSourceReflectionImplWrapper;
import tech.intellispaces.reflections.framework.annotation.Configuration;
import tech.intellispaces.reflections.framework.annotation.Projection;
import tech.intellispaces.reflections.framework.annotation.Properties;

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
  public MovableTransactionFactoryReflection transactionFactory(MovableDataSource dataSource) {
    return new TransactionFactoryOverDataSourceReflectionImplWrapper(dataSource);
  }
}
