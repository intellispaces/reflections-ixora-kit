package tech.intellispaces.ixora.rdb.query;

import tech.intellispaces.ixora.data.collection.UnmovableList;
import tech.intellispaces.ixora.data.collection.UnmovableListHandle;
import tech.intellispaces.jaquarius.object.reference.ObjectHandles;

public interface ParameterizedNamedQueries {

  static UnmovableParameterizedNamedQueryHandle get(
      String query,
      UnmovableList<String> paramNames
  ) {
    return new UnmovableParameterizedNamedQueryDataset(
        null,
        query,
        ObjectHandles.handle(paramNames, UnmovableListHandle.class)
    );
  }
}
