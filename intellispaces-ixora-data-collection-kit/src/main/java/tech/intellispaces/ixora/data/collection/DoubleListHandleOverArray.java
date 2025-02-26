package tech.intellispaces.ixora.data.collection;

import tech.intellispaces.commons.base.type.Type;
import tech.intellispaces.commons.base.type.Types;
import tech.intellispaces.jaquarius.annotation.Mapper;
import tech.intellispaces.jaquarius.annotation.ObjectHandle;

import java.util.Arrays;

@ObjectHandle(Float64ListDomain.class)
abstract class DoubleListHandleOverArray implements UnmovableFloat64ListHandle {
  private final double[] array;
  private final Type<Double> elementType = Types.get(Double.class);
  private java.util.List<Double> list;

  DoubleListHandleOverArray(double[] array) {
    this.array = array;
  }

  DoubleListHandleOverArray(java.util.List<Double> list) {
    this.array = list.stream().mapToDouble(d -> d).toArray();
    this.list = list;
  }

  public double[] array() {
    return array;
  }

  @Mapper
  @Override
  public UnmovableCollectionHandle<Double> asCollection() {
    return new JavaCollectionHandleWrapper<>(nativeList(), elementType);
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
  public Integer size() {
    return getSize();
  }

  @Mapper
  @Override
  public int sizeAsPrimitive() {
    return getSize();
  }

  private int getSize() {
    return array.length;
  }

  @Override
  public java.util.List<Double> nativeList() {
    if (list == null) {
      list = Arrays.stream(array).boxed().toList();
    }
    return list;
  }

  @Override
  public java.util.Collection<Double> nativeCollection() {
    return nativeList();
  }
}
