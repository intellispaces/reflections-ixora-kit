package tech.intellispaces.ixora.data.collection;

import tech.intellispaces.commons.base.type.Type;
import tech.intellispaces.ixora.data.collection.UnmovableCollectionHandle;

public interface Collections {

  /**
   * Creates handle of unmovable collection based on Java list.
   *
   * @param list origin collection.
   * @param elementClass elements class.
   * @return handle to collection.
   * @param <E> element type.
   */
  static <E> UnmovableCollectionHandle<E> of(java.util.List<E> list, Class<E> elementClass) {
    return new JavaCollectionHandleWrapper<>(list, elementClass);
  }

  /**
   * Creates handle of unmovable collection based on Java list.
   *
   * @param list origin collection.
   * @param elementType elements type.
   * @return handle to collection.
   * @param <E> element type.
   */
  static <E> UnmovableCollectionHandle<E> of(java.util.List<E> list, Type<E> elementType) {
    return new JavaCollectionHandleWrapper<>(list, elementType);
  }
}
