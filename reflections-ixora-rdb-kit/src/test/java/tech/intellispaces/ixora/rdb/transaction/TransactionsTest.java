package tech.intellispaces.ixora.rdb.transaction;

import org.junit.jupiter.api.Test;
import tech.intellispaces.reflections.framework.exception.TraverseException;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Tests for {@link Transactions} class.
 */
public class TransactionsTest {

  @Test
  public void test_whenNoTransaction() {
    assertThrows(TraverseException.class, Transactions::current, "Current transaction is not defined");
  }
}
