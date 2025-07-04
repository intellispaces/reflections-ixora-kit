package tech.intellispaces.ixora.data.collection;

import java.util.Iterator;

import tech.intellispaces.commons.type.Type;
import tech.intellispaces.commons.type.Types;
import tech.intellispaces.reflections.framework.annotation.Mapper;
import tech.intellispaces.reflections.framework.annotation.Reflection;
import tech.intellispaces.reflections.framework.reflection.NativeReflectionPoint;

@Reflection(domainClass = CollectionDomain.class)
abstract class JavaCollectionReflection<E> implements Collection<E>, NativeReflectionPoint {
  private final java.util.Collection<E> collection;
  private final Type<E> elementDomain;

  JavaCollectionReflection(java.util.Collection<E> collection, Class<E> elementClass) {
    this.collection = collection;
    this.elementDomain = Types.get(elementClass);
  }

  JavaCollectionReflection(java.util.Collection<E> collection, Type<E> elementType) {
    this.collection = collection;
    this.elementDomain = elementType;
  }

  @Override
  public java.util.Collection<E> boundObject() {
    return collection;
  }

  @Mapper
  @Override
  public Type<E> elementDomain() {
    return elementDomain;
  }

  @Mapper
  @Override
  public int size() {
    return collection.size();
  }

  @Override
  public Iterator<E> iterator() {
    return collection.iterator();
  }
}
