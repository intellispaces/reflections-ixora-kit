package tech.intellispaces.ixora.data.collection;

import tech.intellispaces.commons.base.type.Type;

public interface Lists {

  /**
   * Creates handle of unmovable list based on Java list.
   *
   * @param list origin list.
   * @param elementClass elements class.
   * @return handle to list.
   * @param <E> element type.
   */
  static <E> UnmovableList<E> of(java.util.List<E> list, Class<E> elementClass) {
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
  static <E> UnmovableList<E> of(java.util.List<E> list, Type<E> elementType) {
    return new JavaListWrapper<>(list, elementType);
  }

  static <E> UnmovableList<E> empty(Class<E> elementClass) {
    return null;
  }

  static UnmovableByteList ofBytes(byte[] array) {
    return new ByteListOverArrayWrapper(array);
  }

  static UnmovableByteList ofBytes(java.util.List<Byte> list) {
    return new ByteListOverArrayWrapper(list);
  }

  static UnmovableByteList ofBytes(byte value1, byte value2) {
    return new ByteListOverArrayWrapper(new byte[] { value1, value2 });
  }

  static UnmovableByteList ofBytes(byte value1, byte value2, byte value3) {
    return new ByteListOverArrayWrapper(new byte[] { value1, value2, value3 });
  }

  static UnmovableInteger32List ofIntegers(int[] array) {
    return new IntegerListOverArrayWrapper(array);
  }

  static UnmovableInteger32List ofIntegers(java.util.List<Integer> list) {
    return new IntegerListOverArrayWrapper(list);
  }

  static UnmovableInteger32List ofIntegers(int value1, int value2) {
    return new IntegerListOverArrayWrapper(new int[] { value1, value2 });
  }

  static UnmovableInteger32List ofIntegers(int value1, int value2, int value3) {
    return new IntegerListOverArrayWrapper(new int[] { value1, value2, value3 });
  }

  static UnmovableFloat64List ofDoubles(double[] array) {
    return new DoubleListOverArrayWrapper(array);
  }

  static UnmovableFloat64List ofDoubles(java.util.List<Double> list) {
    return new DoubleListOverArrayWrapper(list);
  }

  static UnmovableList<String> ofStrings(java.util.List<String> list) {
    return of(list, String.class);
  }
}
