package tech.intellispaces.jaquarius.ixora.rdb.transaction;

import tech.intellispaces.jaquarius.annotation.Mapper;
import tech.intellispaces.jaquarius.annotation.MapperOfMoving;
import tech.intellispaces.jaquarius.annotation.ObjectHandle;
import tech.intellispaces.jaquarius.ixora.rdb.datasource.DataSourceHandle;
import tech.intellispaces.jaquarius.ixora.rdb.datasource.MovableConnectionHandle;
import tech.intellispaces.jaquarius.ixora.rdb.datasource.MovableDataSourceHandle;
import tech.intellispaces.jaquarius.ixora.rdb.exception.TransactionException;

@ObjectHandle(TransactionFactoryDomain.class)
abstract class TransactionFactoryHandleOverDataSource implements MovableTransactionFactoryHandle {
  private final MovableDataSourceHandle dataSource;

  TransactionFactoryHandleOverDataSource(MovableDataSourceHandle dataSource) {
    this.dataSource = dataSource;
  }

  @Mapper
  @Override
  public DataSourceHandle dataSource() {
    return dataSource;
  }

  @Override
  @MapperOfMoving
  public MovableTransactionHandle getTransaction() throws TransactionException {
    MovableConnectionHandle connection = dataSource.getConnection();
    return new TransactionHandleOverConnectionWrapper(connection);
  }
}
