package tech.intellispaces.ixora.rdb.transaction;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import tech.intellispaces.core.Module;
import tech.intellispaces.reflections.framework.ReflectionsFramework;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * Tests for {@link TransactionFunctions} class.
 */
public class TransactionFunctionsTest {
  private Module module;

  @BeforeEach
  public void init() {
    module = ReflectionsFramework.loadModule().start();
  }

  @AfterEach
  public void deinit() {
    module.stop().upload();
  }

  @Test
  public void testTransactional_whenOk() {
    // Given
    var transactionFactory = Mockito.mock(MovableTransactionFactory.class);
    var tx = Mockito.mock(MovableTransactionReflection.class);
    when(transactionFactory.getTransaction()).thenReturn(tx);

    List<Transaction> appliedTransactions = new ArrayList<>();

    // When
    TransactionFunctions.transactional(transactionFactory,
        () -> appliedTransactions.add(Transactions.current())
    );

    // Then
    Assertions.assertThat(appliedTransactions).containsExactly(tx);
    verify(tx).commit();
    verify(tx, never()).rollback();
  }
}
