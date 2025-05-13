package tech.intellispaces.ixora.data.association;

import tech.intellispaces.reflections.framework.annotation.Factory;

@Factory
public class PropertiesSetFactory implements PropertiesSetAssistantCustomizer {

  public UnmovablePropertiesSetReflection handleOf(java.util.Map<String, Object> map) {
    return new PropertiesSetReflectionBasedOnMapWrapper(map);
  }
}
