package tech.intellispaces.ixora.data.snakeyaml;

import org.yaml.snakeyaml.Yaml;
import tech.intellispaces.ixora.data.association.Dictionaries;
import tech.intellispaces.ixora.data.association.exception.InvalidPropertyException;
import tech.intellispaces.ixora.data.association.exception.InvalidPropertyExceptions;
import tech.intellispaces.jaquarius.annotation.Guide;
import tech.intellispaces.jaquarius.annotation.Mapper;
import tech.intellispaces.ixora.data.dictionary.DictionaryHandle;
import tech.intellispaces.ixora.data.yaml.StringToDictionaryGuide;

@Guide
public class SnakeyamlGuide implements StringToDictionaryGuide {

  @Mapper
  @Override
  public DictionaryHandle stringToDictionary(String string) throws InvalidPropertyException {
    try {
      var yaml = new Yaml();
      return Dictionaries.of(yaml.load(string));
    } catch (Exception e) {
      throw InvalidPropertyExceptions.withCauseAndMessage(e, "Failed to read YAML string");
    }
  }
}
