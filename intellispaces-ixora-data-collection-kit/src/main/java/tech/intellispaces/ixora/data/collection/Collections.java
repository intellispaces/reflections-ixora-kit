package tech.intellispaces.ixora.data.collection;

import tech.intellispaces.commons.type.Type;

public interface Collections {

  /**
   * Creates handle of unmovable collection based on Java list.
   *
   * @param list origin collection.
   * @param elementClass elements class.
   * @return handle to collection.
   * @param <E> element type.
   */
  static <E> UnmovableCollection<E> of(java.util.List<E> list, Class<E> elementClass) {
    return new JavaCollectionWrapper<>(list, elementClass);
  }

  /**
   * Creates handle of unmovable collection based on Java list.
   *
   * @param list origin collection.
   * @param elementType elements type.
   * @return handle to collection.
   * @param <E> element type.
   */
  static <E> UnmovableCollection<E> of(java.util.List<E> list, Type<E> elementType) {
    return new JavaCollectionWrapper<>(list, elementType);
  }
}
