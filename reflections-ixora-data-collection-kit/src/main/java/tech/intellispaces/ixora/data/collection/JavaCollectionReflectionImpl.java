package tech.intellispaces.ixora.data.collection;

import java.util.Collection;
import java.util.Iterator;

import tech.intellispaces.commons.type.Type;
import tech.intellispaces.commons.type.Types;
import tech.intellispaces.reflections.framework.annotation.Mapper;
import tech.intellispaces.reflections.framework.annotation.Reflection;
import tech.intellispaces.reflections.framework.reflection.NativeReflection;

@Reflection(CollectionDomain.class)
abstract class JavaCollectionReflectionImpl<E> implements
    UnmovableCollection<E>,
    UnmovableCollectionReflection<E>,
    NativeReflection<CollectionDomain<E>>
{
  private final Collection<E> collection;
  private final Type<E> elementDomain;

  JavaCollectionReflectionImpl(Collection<E> collection, Class<E> elementClass) {
    this.collection = collection;
    this.elementDomain = Types.get(elementClass);
  }

  JavaCollectionReflectionImpl(Collection<E> collection, Type<E> elementType) {
    this.collection = collection;
    this.elementDomain = elementType;
  }

  @Override
  public Collection<E> boundObject() {
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
