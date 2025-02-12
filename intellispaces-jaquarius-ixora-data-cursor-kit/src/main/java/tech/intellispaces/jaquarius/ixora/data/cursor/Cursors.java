package tech.intellispaces.jaquarius.ixora.data.cursor;

import tech.intellispaces.jaquarius.ixora.data.collection.CollectionDomain;
import tech.intellispaces.jaquarius.ixora.data.cursor.CursorHandle;

public interface Cursors {

  /**
   * Creates new cursor.
   *
   * @param collection source collection.
   * @return created cursor.
   * @param <E> collection elements type.
   */
  static <E> CursorHandle<E> get(CollectionDomain<E> collection) {
    throw new RuntimeException("Not implemented");
  }
}
