package tech.intellispaces.ixora.data.collection;

import java.util.List;

import tech.intellispaces.commons.type.Type;
import tech.intellispaces.reflections.framework.annotation.Factory;

@Factory
public class CollectionFactory implements CollectionAssistantCustomizer {

  @Override
  public <E> Collection<E> reflectionOf(List<E> list, Class<E> elementClass) {
    return new JavaCollectionReflectionWrapper<>(list, elementClass);
  }

  @Override
  public <E> Collection<E> reflectionOf(List<E> list, Type<E> elementType) {
    return new JavaCollectionReflectionWrapper<>(list, elementType);
  }
}
