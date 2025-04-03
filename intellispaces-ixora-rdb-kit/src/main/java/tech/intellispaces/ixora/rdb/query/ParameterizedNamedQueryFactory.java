package tech.intellispaces.ixora.rdb.query;

import tech.intellispaces.ixora.data.collection.UnmovableList;
import tech.intellispaces.ixora.data.collection.UnmovableListHandle;
import tech.intellispaces.jaquarius.annotation.ObjectFactory;
import tech.intellispaces.jaquarius.object.reference.ObjectHandles;

@ObjectFactory
public class ParameterizedNamedQueryFactory implements ParameterizedNamedQueryAssistantExtension {

  @Override
  public UnmovableParameterizedNamedQueryHandle create(String query, UnmovableList<String> paramNames) {
    return new UnmovableParameterizedNamedQueryDataset(
        null,
        query,
        ObjectHandles.handle(paramNames, UnmovableListHandle.class)
    );
  }
}
