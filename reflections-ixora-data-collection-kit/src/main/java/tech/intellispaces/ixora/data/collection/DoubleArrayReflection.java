package tech.intellispaces.ixora.data.collection;

import java.util.Arrays;
import java.util.Iterator;

import tech.intellispaces.commons.type.Type;
import tech.intellispaces.commons.type.Types;
import tech.intellispaces.reflections.framework.annotation.Mapper;
import tech.intellispaces.reflections.framework.annotation.Reflection;

@Reflection(domainClass = Real64ListDomain.class)
abstract class DoubleArrayReflection implements Real64List {
  private final double[] array;
  private final Type<Double> elementType = Types.get(Double.class);
  private java.util.List<Double> list;

  DoubleArrayReflection(double[] array) {
    this.array = array;
  }

  DoubleArrayReflection(java.util.List<Double> list) {
    this.array = list.stream().mapToDouble(d -> d).toArray();
    this.list = list;
  }

  public double[] array() {
    return array;
  }

  @Mapper
  @Override
  public Collection<Double> asCollection() {
    return new JavaCollectionReflectionWrapper<>(list(), elementType);
  }

  @Mapper
  @Override
  public Type<Double> elementDomain() {
    return elementType;
  }

  @Mapper
  @Override
  public Double get(int index) {
    return getElement(index);
  }

  @Mapper
  @Override
  public double getAsPrimitive(int index) {
    return getElement(index);
  }

  private double getElement(int index) {
    return array[index];
  }

  @Mapper
  @Override
  public int size() {
    return array.length;
  }

  @Override
  public Iterator<Double> iterator() {
    return list().iterator();
  }

  private java.util.List<Double> list() {
    if (list == null) {
      list = Arrays.stream(array).boxed().toList();
    }
    return list;
  }
}
