package tech.intellispaces.jaquarius.ixora.data.collection;

import tech.intellispaces.commons.base.type.Type;
import tech.intellispaces.jaquarius.ixora.data.collection.UnmovableByteListHandle;
import tech.intellispaces.jaquarius.ixora.data.collection.UnmovableFloat64ListHandle;
import tech.intellispaces.jaquarius.ixora.data.collection.UnmovableInteger32ListHandle;
import tech.intellispaces.jaquarius.ixora.data.collection.UnmovableListHandle;

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
    return new JavaListHandleWrapper<>(list, elementClass);
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
    return new JavaListHandleWrapper<>(list, elementType);
  }

  static <E> UnmovableListHandle<E> empty(Class<E> elementClass) {
    return null;
  }

  static UnmovableByteListHandle ofBytes(byte[] array) {
    return new ByteListHandleOverArrayWrapper(array);
  }

  static UnmovableByteListHandle ofBytes(java.util.List<Byte> list) {
    return new ByteListHandleOverArrayWrapper(list);
  }

  static UnmovableByteListHandle ofBytes(byte value1, byte value2) {
    return new ByteListHandleOverArrayWrapper(new byte[] { value1, value2 });
  }

  static UnmovableByteListHandle ofBytes(byte value1, byte value2, byte value3) {
    return new ByteListHandleOverArrayWrapper(new byte[] { value1, value2, value3 });
  }

  static UnmovableInteger32ListHandle ofIntegers(int[] array) {
    return new IntegerListHandleOverArrayWrapper(array);
  }

  static UnmovableInteger32ListHandle ofIntegers(java.util.List<Integer> list) {
    return new IntegerListHandleOverArrayWrapper(list);
  }

  static UnmovableInteger32ListHandle ofIntegers(int value1, int value2) {
    return new IntegerListHandleOverArrayWrapper(new int[] { value1, value2 });
  }

  static UnmovableInteger32ListHandle ofIntegers(int value1, int value2, int value3) {
    return new IntegerListHandleOverArrayWrapper(new int[] { value1, value2, value3 });
  }

  static UnmovableFloat64ListHandle ofDoubles(double[] array) {
    return new DoubleListHandleOverArrayWrapper(array);
  }

  static UnmovableFloat64ListHandle ofDoubles(java.util.List<Double> list) {
    return new DoubleListHandleOverArrayWrapper(list);
  }

  static UnmovableListHandle<String> ofStrings(java.util.List<String> list) {
    return of(list, String.class);
  }
}
