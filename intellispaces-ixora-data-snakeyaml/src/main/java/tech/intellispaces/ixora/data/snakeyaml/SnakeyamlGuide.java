package tech.intellispaces.ixora.data.snakeyaml;

import org.yaml.snakeyaml.Yaml;

import tech.intellispaces.ixora.data.association.PropertiesHandle;
import tech.intellispaces.ixora.data.association.Propertiess;
import tech.intellispaces.ixora.data.association.exception.InvalidPropertyException;
import tech.intellispaces.ixora.data.association.exception.InvalidPropertyExceptions;
import tech.intellispaces.ixora.data.yaml.YamlStringToPropertiesGuide;
import tech.intellispaces.jaquarius.annotation.Guide;
import tech.intellispaces.jaquarius.annotation.Mapper;

@Guide
public class SnakeyamlGuide implements YamlStringToPropertiesGuide {

  @Mapper
  @Override
  public PropertiesHandle yamlStringToProperties(String string) throws InvalidPropertyException {
    try {
      var yaml = new Yaml();
      return Propertiess.handleOf(yaml.load(string));
    } catch (Exception e) {
      throw InvalidPropertyExceptions.withCauseAndMessage(e, "Failed to parse YAML string");
    }
  }
}
