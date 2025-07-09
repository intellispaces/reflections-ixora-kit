package tech.intellispaces.ixora.rdb.query;

import tech.intellispaces.ixora.data.collection.List;
import tech.intellispaces.reflections.framework.annotation.Factory;
import tech.intellispaces.reflections.framework.reflection.Reflections;

@Factory
public class ParameterizedNamedQueryFactory implements ParameterizedNamedQueryAssistantCustomizer {

  @Override
  public ParameterizedNamedQuery create(String query, List<String> paramNames) {
    return new ParameterizedNamedQueryDataset(query, Reflections.reflection(paramNames, List.class));
  }
}
