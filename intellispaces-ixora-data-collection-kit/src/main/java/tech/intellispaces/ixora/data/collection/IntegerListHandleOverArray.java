package tech.intellispaces.ixora.data.collection;

import tech.intellispaces.commons.base.type.Type;
import tech.intellispaces.commons.base.type.Types;
import tech.intellispaces.jaquarius.annotation.Mapper;
import tech.intellispaces.jaquarius.annotation.ObjectHandle;
import tech.intellispaces.ixora.data.collection.Integer32ListDomain;
import tech.intellispaces.ixora.data.collection.UnmovableCollectionHandle;
import tech.intellispaces.ixora.data.collection.UnmovableInteger32ListHandle;

import java.util.Arrays;
import java.util.List;

@ObjectHandle(Integer32ListDomain.class)
abstract class IntegerListHandleOverArray implements UnmovableInteger32ListHandle {
  private final int[] array;
  private final Type<Integer> elementType = Types.get(Integer.class);
  private List<Integer> list;

  IntegerListHandleOverArray(int[] array) {
    this.array = array;
  }

  IntegerListHandleOverArray(List<Integer> list) {
    this.array = list.stream().mapToInt(i -> i).toArray();
    this.list = list;
  }

  public int[] array() {
    return array;
  }

  @Mapper
  @Override
  public UnmovableCollectionHandle<Integer> asCollection() {
    return new JavaCollectionHandleWrapper<>(nativeList(), elementType);
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

