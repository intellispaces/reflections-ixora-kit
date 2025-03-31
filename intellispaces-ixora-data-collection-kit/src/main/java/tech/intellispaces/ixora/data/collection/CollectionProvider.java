package tech.intellispaces.ixora.data.collection;

import tech.intellispaces.commons.type.Type;
import tech.intellispaces.jaquarius.annotation.ObjectProvider;
import tech.intellispaces.jaquarius.ixora.data.collection.CollectionProviderCustomizer;

import java.util.List;

@ObjectProvider
public class CollectionProvider implements CollectionProviderCustomizer {

  @Override
  public <E> UnmovableCollectionHandle<E> handleOf(List<E> list, Class<E> elementClass) {
    return new JavaCollectionHandleWrapper<>(list, elementClass);
  }

  @Override
  public <E> UnmovableCollectionHandle<E> handleOf(List<E> list, Type<E> elementType) {
    return new JavaCollectionHandleWrapper<>(list, elementType);
  }
}
