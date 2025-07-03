package tech.intellispaces.ixora.data.collection;

import java.util.Iterator;

import tech.intellispaces.commons.type.Type;
import tech.intellispaces.commons.type.Types;
import tech.intellispaces.reflections.framework.annotation.Mapper;
import tech.intellispaces.reflections.framework.annotation.Reflection;
import tech.intellispaces.reflections.framework.reflection.NativePoint;

@Reflection(domainClass = ListDomain.class)
abstract class JavaListReflection<E> implements List<E>, NativePoint {
  private final java.util.List<E> list;
  private final Type<E> elementType;

  JavaListReflection(java.util.List<E> list, Class<E> elementClass) {
    this.list = java.util.Collections.unmodifiableList(list);
    this.elementType = Types.get(elementClass);
  }

  JavaListReflection(java.util.List<E> list, Type<E> elementType) {
    this.list = java.util.Collections.unmodifiableList(list);
    this.elementType = elementType;
  }

  @Override
  public java.util.List<E> boundObject() {
    return list;
  }

  @Mapper
  @Override
  public Collection<E> asCollection() {
    return Collections.reflectionOf(list, elementType);
  }

  @Mapper
  @Override
  public Type<E> elementDomain() {
    return elementType;
  }

  @Mapper
  @Override
  public E get(int index) {
    return list.get(index);
  }

  @Mapper
  @Override
  public int size() {
    return list.size();
  }

  @Override
  public Iterator<E> iterator() {
    return list.iterator();
  }
}
