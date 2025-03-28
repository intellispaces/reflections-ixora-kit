package tech.intellispaces.ixora.data.collection;

import tech.intellispaces.commons.type.Type;
import tech.intellispaces.jaquarius.annotation.ObjectProvider;

@ObjectProvider
public class ListProvider {

  public UnmovableByteListHandle create(byte value1, byte value2) {
    return new ByteArrayHandleWrapper(new byte[] { value1, value2 });
  }

  public UnmovableByteListHandle create(byte value1, byte value2, byte value3) {
    return new ByteArrayHandleWrapper(new byte[] { value1, value2, value3 });
  }

  public UnmovableInteger32ListHandle create(int value1, int value2) {
    return new IntegerArrayHandleWrapper(new int[] { value1, value2 });
  }

  public UnmovableInteger32ListHandle create(int value1, int value2, int value3) {
    return new IntegerArrayHandleWrapper(new int[] { value1, value2, value3 });
  }

  public <E> UnmovableListHandle<E> handleOf(java.util.List<E> list, Class<E> elementClass) {
    return new JavaListHandleWrapper<>(list, elementClass);
  }

  public <E> UnmovableListHandle<E> handleOf(java.util.List<E> list, Type<E> elementType) {
    return new JavaListHandleWrapper<>(list, elementType);
  }

  public UnmovableByteListHandle handleOf(byte[] array) {
    return new ByteArrayHandleWrapper(array);
  }

  public UnmovableInteger32ListHandle handleOf(int[] array) {
    return new IntegerArrayHandleWrapper(array);
  }

  public UnmovableFloat64ListHandle handleOf(double[] array) {
    return new DoubleArrayHandleWrapper(array);
  }

  public UnmovableByteListHandle handleOfByteList(java.util.List<Byte> list) {
    return new ByteArrayHandleWrapper(list);
  }

  public UnmovableInteger32ListHandle handleOfIntegerList(java.util.List<Integer> list) {
    return new IntegerArrayHandleWrapper(list);
  }

  public UnmovableFloat64ListHandle handleOfDoubleList(java.util.List<Double> list) {
    return new DoubleArrayHandleWrapper(list);
  }
}
