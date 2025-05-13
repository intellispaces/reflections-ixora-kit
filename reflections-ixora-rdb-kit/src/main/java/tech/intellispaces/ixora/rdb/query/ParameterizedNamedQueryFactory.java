package tech.intellispaces.ixora.rdb.query;

import tech.intellispaces.ixora.data.collection.UnmovableList;
import tech.intellispaces.ixora.data.collection.UnmovableListReflection;
import tech.intellispaces.reflections.framework.annotation.Factory;
import tech.intellispaces.reflections.framework.reflection.Reflections;

@Factory
public class ParameterizedNamedQueryFactory implements ParameterizedNamedQueryAssistantCustomizer {

  @Override
  public UnmovableParameterizedNamedQueryReflection create(String query, UnmovableList<String> paramNames) {
    return new UnmovableParameterizedNamedQueryDataset(
        null,
        query,
            Reflections.reflection(paramNames, UnmovableListReflection.class)
    );
  }
}
