package tech.intellispaces.ixora.data.collection;

import tech.intellispaces.commons.exception.UnexpectedExceptions;
import tech.intellispaces.commons.type.Type;

public interface Lists {

  /**
   * Creates handle of unmovable list based on Java list.
   *
   * @param list origin list.
   * @param elementClass elements class.
   * @return handle to list.
   * @param <E> element type.
   */
  static <E> UnmovableListHandle<E> of(java.util.List<E> list, Class<E> elementClass) {
    return new JavaListWrapper<>(list, elementClass);
  }

  /**
   * Creates handle of unmovable list based on Java list.
   *
   * @param list origin list.
   * @param elementType elements type.
   * @return handle to list.
   * @param <E> element type.
   */
  static <E> UnmovableListHandle<E> of(java.util.List<E> list, Type<E> elementType) {
    return new JavaListWrapper<>(list, elementType);
  }

  static <E> UnmovableListHandle<E> empty(Class<E> elementClass) {
    return null;
  }

  static UnmovableByteListHandle ofBytes(byte[] array) {
    return new ByteListOverArrayWrapper(array);
  }

  static UnmovableByteListHandle ofBytes(java.util.List<Byte> list) {
    return new ByteListOverArrayWrapper(list);
  }

  static UnmovableByteListHandle ofBytes(byte value1, byte value2) {
    return new ByteListOverArrayWrapper(new byte[] { value1, value2 });
  }

  static UnmovableByteListHandle ofBytes(byte value1, byte value2, byte value3) {
    return new ByteListOverArrayWrapper(new byte[] { value1, value2, value3 });
  }

  static UnmovableInteger32ListHandle ofIntegers(int[] array) {
    return new IntegerListOverArrayWrapper(array);
  }

  static UnmovableInteger32ListHandle ofIntegers(java.util.List<Integer> list) {
    return new IntegerListOverArrayWrapper(list);
  }

  static UnmovableInteger32ListHandle ofIntegers(int value1, int value2) {
    return new IntegerListOverArrayWrapper(new int[] { value1, value2 });
  }

  static UnmovableInteger32ListHandle ofIntegers(int value1, int value2, int value3) {
    return new IntegerListOverArrayWrapper(new int[] { value1, value2, value3 });
  }

  static UnmovableFloat64ListHandle ofDoubles(double[] array) {
    return new DoubleListOverArrayWrapper(array);
  }

  static UnmovableFloat64ListHandle ofDoubles(java.util.List<Double> list) {
    return new DoubleListOverArrayWrapper(list);
  }

  static UnmovableListHandle<String> ofStrings(java.util.List<String> list) {
    return of(list, String.class);
  }

  @SuppressWarnings("unchecked")
  static <E> ListHandle<E> listHandle(Object handle, Class<E> elementClass) {
    if (handle == null) {
      return null;
    }
    if (ListHandle.class.isAssignableFrom(handle.getClass())) {
      return (ListHandle<E>) handle;
    }
    throw UnexpectedExceptions.withMessage("Not a list handle");
  }
}
