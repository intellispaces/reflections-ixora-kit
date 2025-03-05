package tech.intellispaces.ixora.data.collection;

import tech.intellispaces.commons.base.type.Type;
import tech.intellispaces.commons.base.type.Types;
import tech.intellispaces.jaquarius.annotation.Mapper;
import tech.intellispaces.jaquarius.annotation.ObjectHandle;

import java.util.Arrays;

@ObjectHandle(Float64ListDomain.class)
abstract class DoubleListOverArray implements UnmovableFloat64List {
  private final double[] array;
  private final Type<Double> elementType = Types.get(Double.class);
  private java.util.List<Double> list;

  DoubleListOverArray(double[] array) {
    this.array = array;
  }

  DoubleListOverArray(java.util.List<Double> list) {
    this.array = list.stream().mapToDouble(d -> d).toArray();
    this.list = list;
  }

  public double[] array() {
    return array;
  }

  @Mapper
  @Override
  public UnmovableCollection<Double> asCollection() {
    return new JavaCollectionWrapper<>(nativeList(), elementType);
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
    return getSize();
  }

  @Mapper
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
