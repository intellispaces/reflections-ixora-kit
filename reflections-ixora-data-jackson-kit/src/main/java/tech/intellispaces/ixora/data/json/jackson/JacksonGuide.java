package tech.intellispaces.ixora.data.json.jackson;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import tech.intellispaces.ixora.data.association.PropertiesSet;
import tech.intellispaces.ixora.data.association.PropertiesSets;
import tech.intellispaces.ixora.data.association.exception.InvalidPropertyException;
import tech.intellispaces.ixora.data.association.exception.InvalidPropertyExceptions;
import tech.intellispaces.ixora.data.json.JsonStringToPropertiesSetGuide;
import tech.intellispaces.reflections.framework.annotation.Guide;

@Guide
public class JacksonGuide implements JsonStringToPropertiesSetGuide {

  @Override
  @SuppressWarnings("unchecked")
  public PropertiesSet jsonStringToPropertiesSet(String source) throws InvalidPropertyException {
    try {
      Map<String, Object> map = new ObjectMapper().readValue(source, HashMap.class);
      return PropertiesSets.reflectionOf(map);
    } catch (Exception e) {
      throw InvalidPropertyExceptions.withCauseAndMessage(e, "Failed to parse JSON string");
    }
  }
}
