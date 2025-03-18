package tech.intellispaces.ixora.data.association;

public interface PropertiesProvides {

  static UnmovablePropertiesHandle of(java.util.Map<String, Object> map) {
    return new MapBasedPropertiesWrapper(map);
  }
}
