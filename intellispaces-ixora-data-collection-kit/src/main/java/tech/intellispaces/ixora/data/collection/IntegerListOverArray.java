package tech.intellispaces.ixora.data.collection;

import tech.intellispaces.commons.type.Type;
import tech.intellispaces.commons.type.Types;
import tech.intellispaces.jaquarius.annotation.Mapper;
import tech.intellispaces.jaquarius.annotation.ObjectHandle;

import java.util.Arrays;
import java.util.List;

@ObjectHandle(Integer32ListDomain.class)
abstract class IntegerListOverArray implements UnmovableInteger32ListHandle {
  private final int[] array;
  private final Type<Integer> elementType = Types.get(Integer.class);
  private List<Integer> list;

  IntegerListOverArray(int[] array) {
    this.array = array;
  }

  IntegerListOverArray(List<Integer> list) {
    this.array = list.stream().mapToInt(i -> i).toArray();
    this.list = list;
  }

  public int[] array() {
    return array;
  }

  @Mapper
  @Override
  public UnmovableCollectionHandle<Integer> asCollection() {
    return new JavaCollectionWrapper<>(nativeList(), elementType);
  }

  @Mapper
  @Override
  public Type<Integer> elementDomain() {
    return elementType;
  }

  @Mapper
  @Override
  public Integer get(int index) {
    return getElement(index);
  }

  @Mapper
  @Override
  public int getAsPrimitive(int index) {
    return getElement(index);
  }

  private int getElement(int index) {
    return array[index];
  }

  @Mapper
  @Override
  public int size() {
    return array.length;
  }

  @Override
  public List<Integer> nativeList() {
    if (list == null) {
      list = Arrays.stream(array).boxed().toList();
    }
    return list;
  }

  @Override
  public java.util.Collection<Integer> nativeCollection() {
    return List.of();
  }
}

