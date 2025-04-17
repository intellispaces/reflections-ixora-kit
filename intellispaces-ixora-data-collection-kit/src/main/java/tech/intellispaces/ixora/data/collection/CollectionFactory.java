package tech.intellispaces.ixora.data.collection;

import tech.intellispaces.commons.type.Type;
import tech.intellispaces.jaquarius.annotation.Factory;

import java.util.List;

@Factory
public class CollectionFactory implements CollectionAssistantCustomizer {

  @Override
  public <E> UnmovableCollectionHandle<E> handleOf(List<E> list, Class<E> elementClass) {
    return new JavaCollectionHandleWrapper<>(list, elementClass);
  }

  @Override
  public <E> UnmovableCollectionHandle<E> handleOf(List<E> list, Type<E> elementType) {
    return new JavaCollectionHandleWrapper<>(list, elementType);
  }
}
