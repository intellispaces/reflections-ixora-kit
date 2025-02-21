package tech.intellispaces.ixora.data.association;

import tech.intellispaces.ixora.data.dictionary.UnmovableDictionaryHandle;

public interface Dictionaries {

  static UnmovableDictionaryHandle of(java.util.Map<String, Object> map) {
    return new MapBasedDictionaryWrapper(map);
  }
}
