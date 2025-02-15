package tech.intellispaces.jaquarius.ixora.rdb.query;

import tech.intellispaces.jaquarius.ixora.data.collection.UnmovableListHandle;

public interface ParameterizedNamedQueries {

  static UnmovableParameterizedNamedQueryHandle get(
      String query,
      UnmovableListHandle<String> paramNames
  ) {
    return new ParameterizedNamedQueryDataset(null, query, paramNames);
  }
}
