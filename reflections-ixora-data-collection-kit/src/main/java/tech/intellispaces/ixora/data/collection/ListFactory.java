package tech.intellispaces.ixora.data.collection;

import tech.intellispaces.commons.type.Type;
import tech.intellispaces.reflections.framework.annotation.Factory;

import java.util.List;

@Factory
public class ListFactory implements ListAssistantCustomizer {

  @Override
  public UnmovableByteListReflection create(byte value1, byte value2) {
    return new ByteArrayReflectionWrapper(new byte[] { value1, value2 });
  }

  @Override
  public UnmovableByteListReflection create(byte value1, byte value2, byte value3) {
    return new ByteArrayReflectionWrapper(new byte[] { value1, value2, value3 });
  }

  @Override
  public UnmovableInteger32ListReflection create(int value1, int value2) {
    return new IntegerArrayReflectionWrapper(new int[] { value1, value2 });
  }

  @Override
  public UnmovableInteger32ListReflection create(int value1, int value2, int value3) {
    return new IntegerArrayReflectionWrapper(new int[] { value1, value2, value3 });
  }

  @Override
  public <E> UnmovableListReflection<E> empty(Class<E> elementClass) {
    return handleOf(List.of(), elementClass);
  }

  @Override
  public <E> UnmovableListReflection<E> handleOf(java.util.List<E> list, Class<E> elementClass) {
    return new JavaListReflectionWrapper<>(list, elementClass);
  }

  @Override
  public <E> UnmovableListReflection<E> handleOf(java.util.List<E> list, Type<E> elementType) {
    return new JavaListReflectionWrapper<>(list, elementType);
  }

  @Override
  public UnmovableByteListReflection handleOf(byte[] array) {
    return new ByteArrayReflectionWrapper(array);
  }

  @Override
  public UnmovableInteger32ListReflection handleOf(int[] array) {
    return new IntegerArrayReflectionWrapper(array);
  }

  @Override
  public UnmovableReal64ListReflection handleOf(double[] array) {
    return new DoubleArrayReflectionWrapper(array);
  }

  @Override
  public UnmovableByteListReflection handleOfByteList(java.util.List<Byte> list) {
    return new ByteArrayReflectionWrapper(list);
  }

  @Override
  public UnmovableInteger32ListReflection handleOfIntegerList(java.util.List<Integer> list) {
    return new IntegerArrayReflectionWrapper(list);
  }

  @Override
  public UnmovableReal64ListReflection handleOfDoubleList(java.util.List<Double> list) {
    return new DoubleArrayReflectionWrapper(list);
  }
}
