package tech.intellispaces.ixora.rdb.transaction;

import java.util.ArrayDeque;
import java.util.Deque;

import tech.intellispaces.reflections.framework.annotation.Factory;
import tech.intellispaces.reflections.framework.exception.TraverseExceptions;

@Factory
public class TransactionProvider implements TransactionAssistantCustomizer {
  private static final ThreadLocal<Deque<MovableTransactionReflection>> CURRENT_TRANSACTIONS = new ThreadLocal<>();

  @Override
  public MovableTransactionReflection current() {
    MovableTransactionReflection tx = null;
    Deque<MovableTransactionReflection> transactions = CURRENT_TRANSACTIONS.get();
    if (transactions != null) {
      tx = transactions.peek();
    }
    if (tx == null) {
      throw TraverseExceptions.withMessage("Current transaction is not defined");
    }
    return tx;
  }

  public static void setCurrentTransaction(MovableTransactionReflection tx) {
    if (tx != null) {
      Deque<MovableTransactionReflection> transactions = CURRENT_TRANSACTIONS.get();
      if (transactions == null) {
        transactions = new ArrayDeque<>();
        CURRENT_TRANSACTIONS.set(transactions);
      }
      transactions.push(tx);
    } else {
      Deque<MovableTransactionReflection> transactions = CURRENT_TRANSACTIONS.get();
      if (transactions != null) {
        transactions.pop();
      }
    }
  }
}
