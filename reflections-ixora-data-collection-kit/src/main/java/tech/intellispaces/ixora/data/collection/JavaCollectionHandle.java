package tech.intellispaces.ixora.data.collection;

import tech.intellispaces.commons.type.Type;
import tech.intellispaces.commons.type.Types;
import tech.intellispaces.reflections.framework.annotation.Mapper;
import tech.intellispaces.reflections.framework.annotation.ObjectHandle;

import java.util.Collection;
import java.util.Iterator;

@ObjectHandle(CollectionDomain.class)
abstract class JavaCollectionHandle<E> implements UnmovableCollection<E>, UnmovableCollectionHandle<E> {
  private final Collection<E> collection;
  private final Type<E> elementDomain;

  JavaCollectionHandle(Collection<E> collection, Class<E> elementClass) {
    this.collection = collection;
    this.elementDomain = Types.get(elementClass);
  }

  JavaCollectionHandle(Collection<E> collection, Type<E> elementType) {
    this.collection = collection;
    this.elementDomain = elementType;
  }

  Collection<E> collection() {
    return collection;
  }

  @Override
  public Type<CollectionDomain<E>> domain() {
    return Types.get(CollectionDomain.class, elementDomain);
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
