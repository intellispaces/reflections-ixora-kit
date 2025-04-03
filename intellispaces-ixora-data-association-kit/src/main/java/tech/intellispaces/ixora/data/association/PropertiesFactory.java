package tech.intellispaces.ixora.data.association;

import tech.intellispaces.jaquarius.annotation.ObjectFactory;
import tech.intellispaces.jaquarius.ixora.data.association.PropertiesAssistantExtension;

@ObjectFactory
public class PropertiesFactory implements PropertiesAssistantExtension {

  public UnmovablePropertiesHandle handleOf(java.util.Map<String, Object> map) {
    return new PropertiesHandleBasedOnMapWrapper(map);
  }
}
