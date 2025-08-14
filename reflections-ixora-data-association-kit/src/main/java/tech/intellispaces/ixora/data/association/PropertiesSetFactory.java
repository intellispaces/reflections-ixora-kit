package tech.intellispaces.ixora.data.association;

import tech.intellispaces.reflections.framework.annotation.Factory;

@Factory
public class PropertiesSetFactory implements PropertiesSetAssistantCustomizer {

  public PropertiesSet reflectionOf(tech.intellispaces.commons.properties.PropertiesSet props) {
    return new CommonPropertiesSetReflectionWrapper(props);
  }

  public PropertiesSet reflectionOf(java.util.Map<String, Object> map) {
    return reflectionOf(tech.intellispaces.commons.properties.PropertiesSets.create(map, "."));
  }
}
