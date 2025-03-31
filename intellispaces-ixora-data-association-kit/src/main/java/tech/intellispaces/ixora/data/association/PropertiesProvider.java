package tech.intellispaces.ixora.data.association;

import tech.intellispaces.jaquarius.annotation.ObjectProvider;
import tech.intellispaces.jaquarius.ixora.data.association.PropertiesProviderCustomizer;

@ObjectProvider
public class PropertiesProvider implements PropertiesProviderCustomizer {

  public UnmovablePropertiesHandle handleOf(java.util.Map<String, Object> map) {
    return new PropertiesHandleBasedOnMapWrapper(map);
  }
}
