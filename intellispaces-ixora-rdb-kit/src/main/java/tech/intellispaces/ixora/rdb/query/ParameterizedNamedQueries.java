package tech.intellispaces.ixora.rdb.query;

import tech.intellispaces.ixora.data.collection.UnmovableList;

public interface ParameterizedNamedQueries {

  static UnmovableParameterizedNamedQuery get(
      String query,
      UnmovableList<String> paramNames
  ) {
    return new ParameterizedNamedQueryDataset(null, query, paramNames);
  }
}
