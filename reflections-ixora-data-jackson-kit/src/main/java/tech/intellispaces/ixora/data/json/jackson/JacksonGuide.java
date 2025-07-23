package tech.intellispaces.ixora.data.json.jackson;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import tech.intellispaces.commons.exception.NotImplementedExceptions;
import tech.intellispaces.core.Projection;
import tech.intellispaces.core.ReflectionChannel;
import tech.intellispaces.ixora.data.Dataset;
import tech.intellispaces.ixora.data.association.PropertiesSet;
import tech.intellispaces.ixora.data.association.PropertiesSets;
import tech.intellispaces.ixora.data.association.exception.InvalidPropertyException;
import tech.intellispaces.ixora.data.association.exception.InvalidPropertyExceptions;
import tech.intellispaces.ixora.data.collection.List;
import tech.intellispaces.ixora.data.json.DatasetToJsonStringChannel;
import tech.intellispaces.ixora.data.json.JsonStringToPropertiesSetChannel;
import tech.intellispaces.reflections.framework.annotation.Guide;
import tech.intellispaces.reflections.framework.annotation.Mapper;

@Guide
public class JacksonGuide {
  private final ObjectMapper objectMapper = new ObjectMapper();

  @Mapper(JsonStringToPropertiesSetChannel.class)
  @SuppressWarnings("unchecked")
  public PropertiesSet jsonStringToPropertiesSet(String json) throws InvalidPropertyException {
    try {
      Map<String, Object> map = objectMapper.readValue(json, HashMap.class);
      return PropertiesSets.reflectionOf(map);
    } catch (Exception e) {
      throw InvalidPropertyExceptions.withCauseAndMessage(e, "Failed to parse JSON string");
    }
  }

  @Mapper(DatasetToJsonStringChannel.class)
  public String datasetToJsonString(Dataset dataset) {
    var rootNode = objectMapper.createObjectNode();
    populateObjectNode(rootNode, dataset);
    return rootNode.toString();
  }

  void populateObjectNode(ObjectNode objectNode, Dataset dataset) {
    for (ReflectionChannel channel : dataset.domain().domainChannels()) {
      String alias = channel.alias();
      Object value = getValue(dataset, channel);
      objectNode.set(alias, createNode(objectNode, value));
    }
  }

  Object getValue(Dataset dataset, ReflectionChannel channel) {
    Projection projection = dataset.projectionThru(channel.cid());
    if (projection.isFocused()) {
      return projection.asFocused().target();
    } else if (projection.isUnknown() || projection.isVoid()) {
      return null;
    } else {
      throw NotImplementedExceptions.withCode("OCC1sjVH");
    }
  }

  JsonNode createNode(ObjectNode rootNode, Object value) {
    if (value == null) {
      return rootNode.nullNode();
    } else if (value instanceof Boolean bool) {
      return rootNode.booleanNode(bool);
    } else if (value instanceof Byte number) {
      return rootNode.numberNode(number);
    } else if (value instanceof Short number) {
      return rootNode.numberNode(number);
    } else if (value instanceof Integer number) {
      return rootNode.numberNode(number);
    } else if (value instanceof Long number) {
      return rootNode.numberNode(number);
    } else if (value instanceof Float number) {
      return rootNode.numberNode(number);
    } else if (value instanceof Double number) {
      return rootNode.numberNode(number);
    } else if (value instanceof Character character) {
      return rootNode.textNode("" + character);
    } else if (value instanceof String string) {
      return rootNode.textNode(string);
    } else if (value instanceof List<?> list) {
      ArrayNode arrayNode = rootNode.arrayNode(list.size());
      for (Object element : list) {
        arrayNode.add(createNode(rootNode, element));
      }
      return arrayNode;
    } else if (value instanceof Dataset dataset) {
      ObjectNode objectNode = rootNode.objectNode();
      populateObjectNode(objectNode, dataset);
      return objectNode;
    } else {
      throw NotImplementedExceptions.withCodeAndMessage("XM5UFwlH", value.getClass().getCanonicalName());
    }
  }
}
