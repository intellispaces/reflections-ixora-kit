package tech.intellispaces.ixora.data.association;

public interface PropertiesProvides {

  static UnmovableProperties of(java.util.Map<String, Object> map) {
    return new MapBasedPropertiesWrapper(map);
  }
}
