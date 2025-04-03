package tech.intellispaces.ixora.data.collection;

import tech.intellispaces.commons.type.Type;
import tech.intellispaces.jaquarius.annotation.ObjectFactory;
import tech.intellispaces.jaquarius.ixora.data.collection.CollectionAssistantExtension;

import java.util.List;

@ObjectFactory
public class CollectionFactory implements CollectionAssistantExtension {

  @Override
  public <E> UnmovableCollectionHandle<E> handleOf(List<E> list, Class<E> elementClass) {
    return new JavaCollectionHandleWrapper<>(list, elementClass);
  }

  @Override
  public <E> UnmovableCollectionHandle<E> handleOf(List<E> list, Type<E> elementType) {
    return new JavaCollectionHandleWrapper<>(list, elementType);
  }
}
