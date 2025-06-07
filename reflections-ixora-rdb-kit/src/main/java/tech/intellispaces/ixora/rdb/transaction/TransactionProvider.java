package tech.intellispaces.ixora.rdb.transaction;

import java.util.ArrayDeque;
import java.util.Deque;

import tech.intellispaces.reflections.framework.annotation.Factory;
import tech.intellispaces.reflections.framework.exception.TraverseExceptions;

@Factory
public class TransactionProvider implements TransactionAssistantCustomizer {
  private static final ThreadLocal<Deque<MovableTransaction>> CURRENT_TRANSACTIONS = new ThreadLocal<>();

  @Override
  public MovableTransaction current() {
    MovableTransaction tx = null;
    Deque<MovableTransaction> transactions = CURRENT_TRANSACTIONS.get();
    if (transactions != null) {
      tx = transactions.peek();
    }
    if (tx == null) {
      throw TraverseExceptions.withMessage("Current transaction is not defined");
    }
    return tx;
  }

  public static void setCurrentTransaction(MovableTransaction tx) {
    if (tx != null) {
      Deque<MovableTransaction> transactions = CURRENT_TRANSACTIONS.get();
      if (transactions == null) {
        transactions = new ArrayDeque<>();
        CURRENT_TRANSACTIONS.set(transactions);
      }
      transactions.push(tx);
    } else {
      Deque<MovableTransaction> transactions = CURRENT_TRANSACTIONS.get();
      if (transactions != null) {
        transactions.pop();
      }
    }
  }
}
