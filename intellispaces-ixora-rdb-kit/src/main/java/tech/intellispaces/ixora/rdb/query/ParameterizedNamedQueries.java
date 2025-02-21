package tech.intellispaces.ixora.rdb.query;

import tech.intellispaces.ixora.data.collection.UnmovableListHandle;

public interface ParameterizedNamedQueries {

  static UnmovableParameterizedNamedQueryHandle get(
      String query,
      UnmovableListHandle<String> paramNames
  ) {
    return new ParameterizedNamedQueryDataset(null, query, paramNames);
  }
}
