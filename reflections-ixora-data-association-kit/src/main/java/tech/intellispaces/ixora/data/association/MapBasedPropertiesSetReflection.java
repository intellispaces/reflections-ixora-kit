package tech.intellispaces.ixora.data.association;

import java.util.Collections;
import java.util.Map;

import org.jetbrains.annotations.Nullable;

import tech.intellispaces.commons.properties.PropertiesSets;
import tech.intellispaces.ixora.data.association.exception.InvalidPropertyException;
import tech.intellispaces.ixora.data.association.exception.InvalidPropertyExceptions;
import tech.intellispaces.ixora.data.collection.List;
import tech.intellispaces.ixora.data.collection.Lists;
import tech.intellispaces.reflections.framework.annotation.Mapper;
import tech.intellispaces.reflections.framework.annotation.Reflection;

@Reflection(domainClass = PropertiesSetDomain.class)
abstract class MapBasedPropertiesSetReflection implements PropertiesSet, tech.intellispaces.commons.properties.PropertiesSet {
  private final Map<String, Object> map;
  private tech.intellispaces.commons.properties.PropertiesSet commonPropertiesSet;

  MapBasedPropertiesSetReflection(Map<String, Object> map) {
    this.map = (map != null ? map : Map.of());
  }

  public Map<String, Object> asNativeMap() {
    return Collections.unmodifiableMap(map);
  }

  @Mapper
  @Override
  @SuppressWarnings("unchecked")
  public Object property(String path) throws InvalidPropertyException {
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
      return Lists.empty();
    }
    Object firstElement = list.get(0);
    if (firstElement instanceof Integer) {
      return integer32ListProperty(path, list);
    } else if (firstElement instanceof Double) {
      return float64ListProperty(path, list);
    } else if (firstElement instanceof String) {
      return stringListProperty(path, list);
    } else if (firstElement instanceof Map<?, ?>) {
      return propertiesSetListProperty(path, list);
    } else {
      throw new UnsupportedOperationException("Not implemented");
    }
  }

  @Mapper
  @Override
  public int integer32Property(String path) throws InvalidPropertyException {
    Object value = traverse(path);
    validateSingleValueType(path, value, Integer.class);
    return (int) value;
  }

  @Mapper
  @Override
  public double real64Property(String path) throws InvalidPropertyException {
    Object value = traverse(path);
    validateSingleValueType(path, value, Double.class);
    return (double) value;
  }

  @Mapper
  @Override
  public String stringProperty(String path) throws InvalidPropertyException {
    Object value = traverse(path);
    validateSingleValueType(path, value, String.class);
    return (String) value;
  }

  @Mapper
  @Override
  @SuppressWarnings("unchecked")
  public PropertiesSet propertiesSetProperty(String path) throws InvalidPropertyException {
    if (path.isEmpty()) {
      return this;
    }
    Object value = traverse(path);
    validateSingleValueType(path, value, Map.class);
    return new MapBasedPropertiesSetReflectionWrapper((Map<String, Object>) value);
  }

  @Mapper
  @Override
  public List<Integer> integer32ListProperty(String path) throws InvalidPropertyException {
    Object value = traverse(path);
    return integer32ListProperty(path, value);
  }

  @SuppressWarnings("unchecked")
  private List<Integer> integer32ListProperty(String path, Object value) {
    validateListValueType(path, value, Integer.class);
    return Lists.reflectionOfIntegerList((java.util.List<Integer>) value);
  }

  @Mapper
  @Override
  public List<Double> real64ListProperty(String path) throws InvalidPropertyException {
    Object value = traverse(path);
    return float64ListProperty(path, value);
  }

  @SuppressWarnings("unchecked")
  private List<Double> float64ListProperty(String path, Object value) {
    validateListValueType(path, value, Double.class);
    return Lists.reflectionOfDoubleList((java.util.List<Double>) value);
  }

  @Mapper
  @Override
  public List<String> stringListProperty(String path) throws InvalidPropertyException {
    Object value = traverse(path);
    return stringListProperty(path, value);
  }

  @SuppressWarnings("unchecked")
  private List<String> stringListProperty(String path, Object value) {
    validateListValueType(path, value, String.class);
    return Lists.reflectionOf((java.util.List<String>) value, String.class);
  }

  @Mapper
  @Override
  public List<PropertiesSet> propertiesSetListProperty(String path) throws InvalidPropertyException {
    Object value = traverse(path);
    return propertiesSetListProperty(path, value);
  }

  @SuppressWarnings("unchecked")
  private List<PropertiesSet> propertiesSetListProperty(String path, Object value) {
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

  @Override
  public @Nullable String name() {
    return asCommonPropertiesSet().name();
  }

  @Override
  public java.util.List<String> path() {
    return asCommonPropertiesSet().path();
  }

  @Override
  public String delimiter() {
    return asCommonPropertiesSet().delimiter();
  }

  @Override
  public java.util.List<String> propertiesNames() {
    return asCommonPropertiesSet().propertiesNames();
  }

  @Override
  public boolean hasProperty(String name) {
    return asCommonPropertiesSet().hasProperty(name);
  }

  @Override
  public @Nullable Object propertyValue(String name) {
    return asCommonPropertiesSet().propertyValue(name);
  }

  @Override
  public java.util.List<tech.intellispaces.commons.properties.PropertiesSet> subProperties() {
    return asCommonPropertiesSet().subProperties();
  }

  @Override
  public boolean hasTraversePath(String... path) {
    return asCommonPropertiesSet().hasTraversePath(path);
  }

  @Override
  public boolean traverseTargetIsSpecified(String name) {
    return asCommonPropertiesSet().traverseTargetIsSpecified(name);
  }

  @Override
  public boolean traverseTargetIsString(String... path) {
    return asCommonPropertiesSet().traverseTargetIsString(path);
  }

  @Override
  public boolean traverseTargetIsStringCompatible(String... path) {
    return asCommonPropertiesSet().traverseTargetIsStringCompatible(path);
  }

  @Override
  public boolean traverseTargetIsProperties(String... path) {
    return asCommonPropertiesSet().traverseTargetIsProperties(path);
  }

  @Override
  public boolean traverseTargetIsPropertiesCompatible(String... path) {
    return asCommonPropertiesSet().traverseTargetIsPropertiesCompatible(path);
  }

  @Override
  public @Nullable Object traverse(String... path) {
    return asCommonPropertiesSet().traverse(path);
  }

  @Override
  public @Nullable Integer traverseToInteger(String... path) {
    return asCommonPropertiesSet().traverseToInteger(path);
  }

  @Override
  public @Nullable Integer traverseAndCastToInteger(String... path) {
    return asCommonPropertiesSet().traverseAndCastToInteger(path);
  }

  @Override
  public @Nullable Double traverseToDouble(String... path) {
    return asCommonPropertiesSet().traverseToDouble(path);
  }

  @Override
  public @Nullable Double traverseAndCastToDouble(String... path) {
    return asCommonPropertiesSet().traverseAndCastToDouble(path);
  }

  @Override
  public @Nullable String traverseToString(String... path) {
    return asCommonPropertiesSet().traverseToString(path);
  }

  @Override
  public @Nullable String traverseToString(String[] headPath, String... tailPath) {
    return asCommonPropertiesSet().traverseToString(headPath, tailPath);
  }

  @Override
  public String traverseToStringOrThrow(String... path) {
    return asCommonPropertiesSet().traverseToStringOrThrow(path);
  }

  @Override
  public @Nullable String traverseAndCastToString(String... path) {
    return asCommonPropertiesSet().traverseAndCastToString(path);
  }

  @Override
  public @Nullable tech.intellispaces.commons.properties.PropertiesSet traverseToProperties(String... path) {
    return asCommonPropertiesSet().traverseToProperties(path);
  }

  @Override
  public tech.intellispaces.commons.properties.PropertiesSet traverseToPropertiesOrThrow(String... path) {
    return asCommonPropertiesSet().traverseToPropertiesOrThrow(path);
  }

  @Override
  public @Nullable tech.intellispaces.commons.properties.PropertiesSet traverseAndCastToProperties(String... path) {
    return asCommonPropertiesSet().traverseAndCastToProperties(path);
  }

  @Override
  public @Nullable java.util.List<Integer> traverseToIntegerList(String... path) {
    return asCommonPropertiesSet().traverseToIntegerList(path);
  }

  @Override
  public @Nullable java.util.List<Integer> traverseAndCastToIntegerList(String... path) {
    return asCommonPropertiesSet().traverseAndCastToIntegerList(path);
  }

  @Override
  public @Nullable java.util.List<Double> traverseToDoubleList(String... path) {
    return asCommonPropertiesSet().traverseToDoubleList(path);
  }

  @Override
  public @Nullable java.util.List<Double> traverseAndCastToDoubleList(String... path) {
    return asCommonPropertiesSet().traverseAndCastToDoubleList(path);
  }

  @Override
  public @Nullable java.util.List<String> traverseToStringList(String... path) {
    return asCommonPropertiesSet().traverseToStringList(path);
  }

  @Override
  public @Nullable java.util.List<String> traverseAndCastToStringList(String... path) {
    return asCommonPropertiesSet().traverseAndCastToStringList(path);
  }

  @Override
  public @Nullable java.util.List<tech.intellispaces.commons.properties.PropertiesSet> traverseToPropertiesList(
      String... path
  ) {
    return asCommonPropertiesSet().traverseToPropertiesList(path);
  }

  @Override
  public @Nullable java.util.List<tech.intellispaces.commons.properties.PropertiesSet> traverseToPropertiesList(
      String[] headPath, String... tailPath
  ) {
    return asCommonPropertiesSet().traverseToPropertiesList(headPath, tailPath);
  }

  @Override
  public @Nullable java.util.List<tech.intellispaces.commons.properties.PropertiesSet> traverseAndCastToPropertiesList(
      String... path
  ) {
    return asCommonPropertiesSet().traverseToPropertiesList(path);
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

  private Class<?> getActualType(Object value) {
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

  private tech.intellispaces.commons.properties.PropertiesSet asCommonPropertiesSet() {
    if (commonPropertiesSet == null) {
      commonPropertiesSet = PropertiesSets.create(map, ".");
    }
    return commonPropertiesSet;
  }
}
