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
    return new JavaListHandleImpl<>(list, elementClass);
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
    return new JavaListHandleImpl<>(list, elementType);
  }

  static <E> UnmovableListHandle<E> empty(Class<E> elementClass) {
    return null;
  }

  static UnmovableByteListHandle ofBytes(byte[] array) {
    return new ByteListHandleOverArrayImpl(array);
  }

  static UnmovableByteListHandle ofBytes(java.util.List<Byte> list) {
    return new ByteListHandleOverArrayImpl(list);
  }

  static UnmovableByteListHandle ofBytes(byte value1, byte value2) {
    return new ByteListHandleOverArrayImpl(new byte[] { value1, value2 });
  }

  static UnmovableByteListHandle ofBytes(byte value1, byte value2, byte value3) {
    return new ByteListHandleOverArrayImpl(new byte[] { value1, value2, value3 });
  }

  static UnmovableInteger32ListHandle ofIntegers(int[] array) {
    return new IntegerListHandleOverArrayImpl(array);
  }

  static UnmovableInteger32ListHandle ofIntegers(java.util.List<Integer> list) {
    return new IntegerListHandleOverArrayImpl(list);
  }

  static UnmovableInteger32ListHandle ofIntegers(int value1, int value2) {
    return new IntegerListHandleOverArrayImpl(new int[] { value1, value2 });
  }

  static UnmovableInteger32ListHandle ofIntegers(int value1, int value2, int value3) {
    return new IntegerListHandleOverArrayImpl(new int[] { value1, value2, value3 });
  }

  static UnmovableFloat64ListHandle ofDoubles(double[] array) {
    return new DoubleListHandleOverArrayImpl(array);
  }

  static UnmovableFloat64ListHandle ofDoubles(java.util.List<Double> list) {
    return new DoubleListHandleOverArrayImpl(list);
  }

  static UnmovableListHandle<String> ofStrings(java.util.List<String> list) {
    return of(list, String.class);
  }
}
