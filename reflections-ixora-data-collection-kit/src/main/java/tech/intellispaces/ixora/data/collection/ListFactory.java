package tech.intellispaces.ixora.data.collection;

import tech.intellispaces.commons.type.Type;
import tech.intellispaces.reflections.framework.annotation.Factory;

import java.util.List;

@Factory
public class ListFactory implements ListAssistantCustomizer {

  @Override
  public UnmovableByteListReflection create(byte value1, byte value2) {
    return new ByteArrayReflectionImplWrapper(new byte[] { value1, value2 });
  }

  @Override
  public UnmovableByteListReflection create(byte value1, byte value2, byte value3) {
    return new ByteArrayReflectionImplWrapper(new byte[] { value1, value2, value3 });
  }

  @Override
  public UnmovableInteger32ListReflection create(int value1, int value2) {
    return new IntegerArrayReflectionImplWrapper(new int[] { value1, value2 });
  }

  @Override
  public UnmovableInteger32ListReflection create(int value1, int value2, int value3) {
    return new IntegerArrayReflectionImplWrapper(new int[] { value1, value2, value3 });
  }

  @Override
  public <E> UnmovableListReflection<E> empty(Class<E> elementClass) {
    return reflectionOf(List.of(), elementClass);
  }

  @Override
  public <E> UnmovableListReflection<E> reflectionOf(java.util.List<E> list, Class<E> elementClass) {
    return new JavaListReflectionImplWrapper<>(list, elementClass);
  }

  @Override
  public <E> UnmovableListReflection<E> reflectionOf(java.util.List<E> list, Type<E> elementType) {
    return new JavaListReflectionImplWrapper<>(list, elementType);
  }

  @Override
  public UnmovableByteListReflection reflectionOf(byte[] array) {
    return new ByteArrayReflectionImplWrapper(array);
  }

  @Override
  public UnmovableInteger32ListReflection reflectionOf(int[] array) {
    return new IntegerArrayReflectionImplWrapper(array);
  }

  @Override
  public UnmovableReal64ListReflection reflectionOf(double[] array) {
    return new DoubleArrayReflectionImplWrapper(array);
  }

  @Override
  public UnmovableByteListReflection reflectionOfByteList(java.util.List<Byte> list) {
    return new ByteArrayReflectionImplWrapper(list);
  }

  @Override
  public UnmovableInteger32ListReflection reflectionOfIntegerList(java.util.List<Integer> list) {
    return new IntegerArrayReflectionImplWrapper(list);
  }

  @Override
  public UnmovableReal64ListReflection reflectionOfDoubleList(java.util.List<Double> list) {
    return new DoubleArrayReflectionImplWrapper(list);
  }
}
