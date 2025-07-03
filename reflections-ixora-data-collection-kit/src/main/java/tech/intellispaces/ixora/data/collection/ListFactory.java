package tech.intellispaces.ixora.data.collection;

import tech.intellispaces.commons.type.Type;
import tech.intellispaces.core.ReflectionPoint;
import tech.intellispaces.reflections.framework.annotation.Factory;

@Factory
public class ListFactory implements ListAssistantCustomizer {

  @Override
  public ByteList create(byte value1, byte value2) {
    return new ByteArrayReflectionWrapper(new byte[] { value1, value2 });
  }

  @Override
  public ByteList create(byte value1, byte value2, byte value3) {
    return new ByteArrayReflectionWrapper(new byte[] { value1, value2, value3 });
  }

  @Override
  public Integer32List create(int value1, int value2) {
    return new IntegerArrayReflectionWrapper(new int[] { value1, value2 });
  }

  @Override
  public Integer32List create(int value1, int value2, int value3) {
    return new IntegerArrayReflectionWrapper(new int[] { value1, value2, value3 });
  }

  @Override
  public <E> List<E> empty(Class<E> elementClass) {
    return reflectionOf(java.util.List.of(), elementClass);
  }

  @Override
  public <E> List<E> reflectionOf(java.util.List<E> list, Class<E> elementClass) {
    return new JavaListReflectionWrapper<>(list, elementClass);
  }

  @Override
  public <E> List<E> reflectionOf(java.util.List<E> list, Type<E> elementType) {
    return new JavaListReflectionWrapper<>(list, elementType);
  }

  @Override
  public List<ReflectionPoint> of(ReflectionPoint point) {
    return new JavaListReflectionWrapper<ReflectionPoint>(java.util.List.of(point), (Type<ReflectionPoint>) null);
  }

  @Override
  public ByteList reflectionOf(byte[] array) {
    return new ByteArrayReflectionWrapper(array);
  }

  @Override
  public Integer32List reflectionOf(int[] array) {
    return new IntegerArrayReflectionWrapper(array);
  }

  @Override
  public Real64List reflectionOf(double[] array) {
    return new DoubleArrayReflectionWrapper(array);
  }

  @Override
  public ByteList reflectionOfByteList(java.util.List<Byte> list) {
    return new ByteArrayReflectionWrapper(list);
  }

  @Override
  public Integer32List reflectionOfIntegerList(java.util.List<Integer> list) {
    return new IntegerArrayReflectionWrapper(list);
  }

  @Override
  public Real64List reflectionOfDoubleList(java.util.List<Double> list) {
    return new DoubleArrayReflectionWrapper(list);
  }
}
