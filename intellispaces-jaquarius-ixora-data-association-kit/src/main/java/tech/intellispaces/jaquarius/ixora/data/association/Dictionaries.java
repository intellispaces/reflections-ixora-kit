package tech.intellispaces.jaquarius.ixora.data.association;

import tech.intellispaces.jaquarius.ixora.data.dictionary.UnmovableDictionaryHandle;

public interface Dictionaries {

  static UnmovableDictionaryHandle of(java.util.Map<String, Object> map) {
    return new MapBasedDictionaryWrapper(map);
  }
}
