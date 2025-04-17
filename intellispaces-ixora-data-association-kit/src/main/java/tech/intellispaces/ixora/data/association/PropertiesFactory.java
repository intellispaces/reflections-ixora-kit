package tech.intellispaces.ixora.data.association;

import tech.intellispaces.jaquarius.annotation.Factory;

@Factory
public class PropertiesFactory implements PropertiesAssistantCustomizer {

  public UnmovablePropertiesHandle handleOf(java.util.Map<String, Object> map) {
    return new PropertiesHandleBasedOnMapWrapper(map);
  }
}
