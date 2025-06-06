package tech.intellispaces.ixora.data.collection;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import tech.intellispaces.commons.type.Type;
import tech.intellispaces.commons.type.Types;
import tech.intellispaces.reflections.framework.annotation.Mapper;
import tech.intellispaces.reflections.framework.annotation.Reflection;

@Reflection(domainClass = Integer32ListDomain.class)
abstract class IntegerArrayReflection implements Integer32List {
  private final int[] array;
  private final Type<Integer> elementType = Types.get(Integer.class);
  private List<Integer> list;

  IntegerArrayReflection(int[] array) {
    this.array = array;
  }

  IntegerArrayReflection(List<Integer> list) {
    this.array = list.stream().mapToInt(i -> i).toArray();
    this.list = list;
  }

  public int[] array() {
    return array;
  }

  @Mapper
  @Override
  public Collection<Integer> asCollection() {
    return new JavaCollectionReflectionWrapper<>(list(), elementType);
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
  public Iterator<Integer> iterator() {
    return list().iterator();
  }

  private List<Integer> list() {
    if (list == null) {
      list = Arrays.stream(array).boxed().toList();
    }
    return list;
  }
}

