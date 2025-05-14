package tech.intellispaces.ixora.data.collection;

import tech.intellispaces.commons.type.Type;
import tech.intellispaces.reflections.framework.annotation.Factory;

import java.util.List;

@Factory
public class CollectionFactory implements CollectionAssistantCustomizer {

  @Override
  public <E> UnmovableCollectionReflection<E> reflectionOf(List<E> list, Class<E> elementClass) {
    return new JavaCollectionReflectionImplWrapper<>(list, elementClass);
  }

  @Override
  public <E> UnmovableCollectionReflection<E> reflectionOf(List<E> list, Type<E> elementType) {
    return new JavaCollectionReflectionImplWrapper<>(list, elementType);
  }
}
