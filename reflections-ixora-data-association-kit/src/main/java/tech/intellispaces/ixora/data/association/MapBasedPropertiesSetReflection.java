package tech.intellispaces.ixora.data.association;

import java.util.Collections;
import java.util.Map;

import tech.intellispaces.commons.properties.PropertiesSets;
import tech.intellispaces.ixora.data.association.exception.InvalidPropertyException;
import tech.intellispaces.ixora.data.association.exception.InvalidPropertyExceptions;
import tech.intellispaces.ixora.data.collection.List;
import tech.intellispaces.ixora.data.collection.Lists;
import tech.intellispaces.reflections.framework.annotation.Mapper;
import tech.intellispaces.reflections.framework.annotation.Reflection;

@Reflection(domainClass = PropertiesSetDomain.class)
abstract class MapBasedPropertiesSetReflection implements PropertiesSet, NativePropertiesSetPresentable {
  private final Map<String, Object> map;

  MapBasedPropertiesSetReflection(Map<String, Object> map) {
    this.map = (map != null ? map : Map.of());
  }

  public Map<String, Object> asNativeMap() {
    return Collections.unmodifiableMap(map);
  }

  @Override
  public tech.intellispaces.commons.properties.PropertiesSet asNativePropertiesSet() {
    return PropertiesSets.create(map, ".");
  }

  @Mapper
  @Override
  @SuppressWarnings("unchecked")
  public Object value(String path) throws InvalidPropertyException {
    if (path.isEmpty()) {
      return this;
    }
    Object result = traverse(path);
    if (result == null) {
      return null;
    } else if (result instanceof Integer) {
      return result;
    } else if (result instanceof Double) {
      return result;
    } else if (result instanceof String) {
      return result;
    } else if (result instanceof java.util.List<?> list) {
      return convertObjectToList(path, list);
    } else if (result instanceof Map<?, ?>) {
      return new MapBasedPropertiesSetReflectionWrapper((Map<String, Object>) result);
    } else {
      throw new UnsupportedOperationException("Not implemented");
    }
  }

  private List<?> convertObjectToList(String path, java.util.List<?> list) {
    if (list.isEmpty()) {
      throw new UnsupportedOperationException("Not implemented");
    }
    Object firstElement = list.get(0);
    if (firstElement instanceof Integer) {
      return integer32List(path, list);
    } else if (firstElement instanceof Double) {
      return float64List(path, list);
    } else if (firstElement instanceof String) {
      return stringList(path, list);
    } else if (firstElement instanceof Map<?, ?>) {
      return propertiesSetList(path, list);
    } else {
      throw new UnsupportedOperationException("Not implemented");
    }
  }

  @Mapper
  @Override
  public int integer32Value(String path) throws InvalidPropertyException {
    Object value = traverse(path);
    validateSingleValueType(path, value, Integer.class);
    return (int) value;
  }

  @Mapper
  @Override
  public double real64Value(String path) throws InvalidPropertyException {
    Object value = traverse(path);
    validateSingleValueType(path, value, Double.class);
    return (double) value;
  }

  @Mapper
  @Override
  public String stringValue(String path) throws InvalidPropertyException {
    Object value = traverse(path);
    validateSingleValueType(path, value, String.class);
    return (String) value;
  }

  @Mapper
  @Override
  @SuppressWarnings("unchecked")
  public PropertiesSet propertiesSetValue(String path) throws InvalidPropertyException {
    if (path.isEmpty()) {
      return this;
    }
    Object value = traverse(path);
    validateSingleValueType(path, value, Map.class);
    return new MapBasedPropertiesSetReflectionWrapper((Map<String, Object>) value);
  }

  @Mapper
  @Override
  public List<Integer> integer32List(String path) throws InvalidPropertyException {
    Object value = traverse(path);
    return integer32List(path, value);
  }

  @SuppressWarnings("unchecked")
  private List<Integer> integer32List(String path, Object value) {
    validateListValueType(path, value, Integer.class);
    return Lists.reflectionOfIntegerList((java.util.List<Integer>) value);
  }

  @Mapper
  @Override
  public List<Double> real64List(String path) throws InvalidPropertyException {
    Object value = traverse(path);
    return float64List(path, value);
  }

  @SuppressWarnings("unchecked")
  private List<Double> float64List(String path, Object value) {
    validateListValueType(path, value, Double.class);
    return Lists.reflectionOfDoubleList((java.util.List<Double>) value);
  }

  @Mapper
  @Override
  public List<String> stringList(String path) throws InvalidPropertyException {
    Object value = traverse(path);
    return stringList(path, value);
  }

  @SuppressWarnings("unchecked")
  private List<String> stringList(String path, Object value) {
    validateListValueType(path, value, String.class);
    return Lists.reflectionOf((java.util.List<String>) value, String.class);
  }

  @Mapper
  @Override
  public List<PropertiesSet> propertiesSetList(String path) throws InvalidPropertyException {
    Object value = traverse(path);
    return propertiesSetList(path, value);
  }

  @SuppressWarnings("unchecked")
  private List<PropertiesSet> propertiesSetList(String path, Object value) {
    validateListValueType(path, value, Map.class);
    var values = (java.util.List<Map<String, Object>>) value;
    java.util.List<PropertiesSet> propsList = values.stream()
        .map(MapBasedPropertiesSetReflectionWrapper::new)
        .map(props -> (PropertiesSet) props)
        .toList();
    return Lists.reflectionOf(propsList, PropertiesSet.class);
  }

  @Mapper
  @Override
  public int size() {
    return asNativeMap().size();
  }

  private void validateSingleValueType(String path, Object value, Class<?> expectedType) {
    if (value == null) {
      throw InvalidPropertyExceptions.withMessage("Property does not exist. Path '{0}'", path);
    }
    if (value instanceof PropertiesSet & expectedType != Map.class) {
      throw InvalidPropertyExceptions.withMessage("Expected property value of {0} type, " +
              "but actual is {1}. Path '{2}'",
          expectedType.getCanonicalName(), PropertiesSet.class.getCanonicalName(), path);

    }
    if (!expectedType.isAssignableFrom(value.getClass())) {
      throw InvalidPropertyExceptions.withMessage("Expected property value of {0} type, " +
              "but actual is {1}. Path '{2}'",
          expectedType.getCanonicalName(), getActualType(value).getCanonicalName(), path);
    }
  }

  private void validateListValueType(String path, Object value, Class<?> expectedElementType) {
    if (value == null) {
      throw InvalidPropertyExceptions.withMessage("Property does not exist. Path '{0}'", path);
    }

    if (!java.util.List.class.isAssignableFrom(value.getClass())) {
      throw InvalidPropertyExceptions.withMessage("Expected property list values of type {0}, " +
              "but actual is single value of type {1}. Path '{2}'",
          expectedElementType.getCanonicalName(), getActualType(value).getCanonicalName(), path);
    }

    var list = (java.util.List<?>) value;
    for (Object element : list) {
      if (!expectedElementType.isAssignableFrom(element.getClass())) {
        throw InvalidPropertyExceptions.withMessage("Expected property list of {0} values, " +
                "but actual is list contained {1} values. Path '{2}'",
            expectedElementType.getCanonicalName(), getActualType(element).getCanonicalName(), path);
      }
    }
  }

  private static Class<?> getActualType(Object value) {
    final Class<?> actualType;
    if (PropertiesSet.class.isAssignableFrom(value.getClass())) {
      actualType = PropertiesSet.class;
    } else if (Map.class.isAssignableFrom(value.getClass())) {
      actualType = Map.class;
    } else if (java.util.List.class.isAssignableFrom(value.getClass())) {
      actualType = java.util.List.class;
    } else {
      actualType = value.getClass();
    }
    return actualType;
  }

  @SuppressWarnings("unchecked")
  private Object traverse(String path) {
    if (path == null) {
      return null;
    }
    if (path.isEmpty()) {
      return this;
    }

    Object result = null;
    Map<String, Object> curMap = asNativeMap();
    String[] parts = path.split("\\.");
    for (String part : parts) {
      if (curMap == null) {
        result = null;
        break;
      } else {
        Object target = curMap.get(part);
        if (target == null) {
          result = null;
          break;
        } else if (target instanceof Map) {
          result = target;
          curMap = (Map<String, Object>) target;
        } else {
          result = target;
          curMap = null;
        }
      }
    }
    return result;
  }
}
