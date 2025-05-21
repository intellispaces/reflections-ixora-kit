package tech.intellispaces.ixora.data.snakeyaml;

import org.yaml.snakeyaml.Yaml;

import tech.intellispaces.ixora.data.association.PropertiesSetReflection;
import tech.intellispaces.ixora.data.association.PropertiesSets;
import tech.intellispaces.ixora.data.association.exception.InvalidPropertyException;
import tech.intellispaces.ixora.data.association.exception.InvalidPropertyExceptions;
import tech.intellispaces.ixora.data.yaml.YamlStringToPropertiesSetGuide;
import tech.intellispaces.reflections.framework.annotation.Guide;
import tech.intellispaces.reflections.framework.annotation.Mapper;

@Guide
public class SnakeyamlGuide implements YamlStringToPropertiesSetGuide {

  @Mapper
  @Override
  public PropertiesSetReflection yamlStringToPropertiesSet(String string) throws InvalidPropertyException {
    try {
      var yaml = new Yaml();
      return PropertiesSets.reflectionOf(yaml.load(string));
    } catch (Exception e) {
      throw InvalidPropertyExceptions.withCauseAndMessage(e, "Failed to parse YAML string");
    }
  }
}
