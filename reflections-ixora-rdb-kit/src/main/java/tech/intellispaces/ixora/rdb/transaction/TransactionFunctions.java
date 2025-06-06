package tech.intellispaces.ixora.rdb.transaction;

import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tech.intellispaces.commons.exception.WrappedExceptions;
import tech.intellispaces.commons.function.ThrowingFunction;
import tech.intellispaces.ixora.rdb.exception.TransactionException;
import tech.intellispaces.ixora.rdb.exception.TransactionExceptions;
import tech.intellispaces.reflections.framework.system.projection.ContextProjections;

/**
 * Transaction functions.
 */
public class TransactionFunctions {
  private static final String TRANSACTION_PROJECTION_NAME = "tx";
  private static final Logger LOG = LoggerFactory.getLogger(TransactionFunctions.class);

  private TransactionFunctions() {}

  public static void transactional(MovableTransactionFactory factory, Runnable operation) {
    transactional(factory,
        data -> {
          operation.run();
          return null;
        },
        null
    );
  }

  public static void transactional(MovableTransactionFactory factory, Consumer<Transaction> operation) {
    transactional(factory,
        data -> {
          operation.accept(Transactions.current());
          return null;
        },
        null
    );
  }

  public static <R, E extends Exception> R transactional(
      MovableTransactionFactory factory,
      ThrowingFunction<Object[], R, E> operation,
      Object[] data
  ) {
    R result;
    MovableTransaction tx = null;
    try {
      tx = (MovableTransaction) factory.getTransaction();
      storeTransactionInContext(tx);
      result = operation.applyThrows(data);
      tx.commit();
    } catch (TransactionException e) {
      LOG.error("Unexpected exception occurred while transaction was executed. Transaction will be rolled back");
      rollback(tx, e);
      throw e;
    } catch (RuntimeException | Error e) {
      if (tx == null) {
        throw TransactionExceptions.withCauseAndMessage(e, "Could not get transaction");
      }
      LOG.error("Runtime exception {} occurred while transaction was executed. Transaction will be rolled back",
          e.getClass().getCanonicalName());
      rollback(tx, e);
      throw TransactionExceptions.withCauseAndMessage(e, "Could not execute transaction");
    } catch (Exception e) {
      if (tx == null) {
        throw TransactionExceptions.withCauseAndMessage(e, "Could not get transaction");
      }
      LOG.info("Checked exception {} occurred while transaction was executed. Transaction will be committed",
          e.getClass().getCanonicalName());
      commit(tx, e);
      throw WrappedExceptions.of(e);
    } finally {
      if (tx != null) {
        removeTransactionFromContext();
      }
    }
    return result;
  }

  private static void storeTransactionInContext(MovableTransaction tx) {
    TransactionProvider.setCurrentTransaction(tx);
    ContextProjections.add(TRANSACTION_PROJECTION_NAME, MovableTransaction.class, tx);
  }

  private static void removeTransactionFromContext() {
    TransactionProvider.setCurrentTransaction(null);
    ContextProjections.remove(TRANSACTION_PROJECTION_NAME);
  }

  private static void commit(MovableTransaction tx, Throwable reason) {
    try {
      tx.commit();
    } catch (Throwable e) {
      if (reason != null) {
        TransactionException te = TransactionExceptions.withCauseAndMessage(e,
            "Could not commit transaction after exception {0}", reason.getClass().getCanonicalName());
        te.addSuppressed(reason);
        throw te;
      }
      throw TransactionExceptions.withCauseAndMessage(e, "Could not commit transaction");
    }
  }

  private static void rollback(MovableTransaction tx, Throwable reason) {
    try {
      tx.rollback();
    } catch (Throwable e) {
      if (reason != null) {
        TransactionException te = TransactionExceptions.withCauseAndMessage(e,
            "Could not roll back transaction after exception {0}", reason.getClass().getCanonicalName());
        te.addSuppressed(reason);
        throw te;
      }
      throw TransactionExceptions.withCauseAndMessage(e, "Could not roll back transaction");
    }
  }
}
