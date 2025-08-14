package tech.intellispaces.ixora.data.association;

import java.util.ArrayList;

import org.jetbrains.annotations.Nullable;

import tech.intellispaces.commons.exception.NotImplementedExceptions;
import tech.intellispaces.commons.exception.UnexpectedExceptions;
import tech.intellispaces.commons.properties.TraversablePropertiesSet;
import tech.intellispaces.commons.text.StringFunctions;
import tech.intellispaces.ixora.data.association.exception.InvalidPropertyException;
import tech.intellispaces.ixora.data.association.exception.InvalidPropertyExceptions;
import tech.intellispaces.ixora.data.collection.List;
import tech.intellispaces.ixora.data.collection.Lists;
import tech.intellispaces.reflections.framework.annotation.Mapper;
import tech.intellispaces.reflections.framework.annotation.Reflection;

@Reflection(domainClass = PropertiesSetDomain.class)
abstract class CommonPropertiesSetReflection implements PropertiesSet, TraversablePropertiesSet {
  private final TraversablePropertiesSet props;

  CommonPropertiesSetReflection(tech.intellispaces.commons.properties.PropertiesSet props) {
    this.props = tech.intellispaces.commons.properties.PropertiesSets.createFlowingTraversable(props);
  }

  private CommonPropertiesSetReflection(TraversablePropertiesSet props) {
    this.props = props;
  }

  @Mapper
  @Override
  public Object property(String path) throws InvalidPropertyException {
    Object value = convertValue(props.traverse(splitPath(path)));
    if (value == null) {
      throw InvalidPropertyExceptions.withMessage("Property does not exist. Path '{0}'", path);
    }
    return value;
  }

  @Mapper
  @Override
  public int integer32Property(String path) throws InvalidPropertyException {
    Integer value = props.traverseToInteger(splitPath(path));
    if (value == null) {
      throw InvalidPropertyExceptions.withMessage("Property does not exist. Path '{0}'", path);
    }
    return value;
  }

  @Mapper
  @Override
  public double real64Property(String path) throws InvalidPropertyException {
    Double value = props.traverseToDouble(splitPath(path));
    if (value == null) {
      throw InvalidPropertyExceptions.withMessage("Property does not exist. Path '{0}'", path);
    }
    return value;
  }

  @Mapper
  @Override
  public String stringProperty(String path) throws InvalidPropertyException {
    String value = props.traverseToString(splitPath(path));
    if (value == null) {
      throw InvalidPropertyExceptions.withMessage("Property does not exist. Path '{0}'", path);
    }
    return value;
  }

  @Mapper
  @Override
  public PropertiesSet propertiesSetProperty(String path) throws InvalidPropertyException {
    TraversablePropertiesSet value = props.traverseToProperties(splitPath(path));
    if (value == null) {
      throw InvalidPropertyExceptions.withMessage("Property does not exist. Path '{0}'", path);
    }
    return new CommonPropertiesSetReflectionWrapper(value);
  }

  @Mapper
  @Override
  public List<Integer> integer32ListProperty(String path) throws InvalidPropertyException {
    java.util.List<Integer> value = props.traverseToIntegerList(splitPath(path));
    if (value == null) {
      throw InvalidPropertyExceptions.withMessage("Property does not exist. Path '{0}'", path);
    }
    return Lists.reflectionOfIntegerList(value);
  }

  @Mapper
  @Override
  public List<Double> real64ListProperty(String path) throws InvalidPropertyException {
    java.util.List<Double> value = props.traverseToDoubleList(splitPath(path));
    if (value == null) {
      throw InvalidPropertyExceptions.withMessage("Property does not exist. Path '{0}'", path);
    }
    return Lists.reflectionOfDoubleList(value);
  }

  @Mapper
  @Override
  public List<String> stringListProperty(String path) throws InvalidPropertyException {
    java.util.List<String> value = props.traverseToStringList(splitPath(path));
    if (value == null) {
      throw InvalidPropertyExceptions.withMessage("Property does not exist. Path '{0}'", path);
    }
    return Lists.reflectionOf(value, String.class);
  }

  @Mapper
  @Override
  public List<PropertiesSet> propertiesSetListProperty(String path) throws InvalidPropertyException {
    java.util.List<TraversablePropertiesSet> values = props.traverseToPropertiesList(splitPath(path));
    if (values == null) {
      throw InvalidPropertyExceptions.withMessage("Property does not exist. Path '{0}'", path);
    }
    java.util.List<PropertiesSet> propsList = values.stream()
        .map(CommonPropertiesSetReflectionWrapper::new)
        .map(props -> (PropertiesSet) props)
        .toList();
    return Lists.reflectionOf(propsList, PropertiesSet.class);
  }

  @Mapper
  @Override
  public int size() {
    return props.size();
  }

  @Override
  public @Nullable String name() {
    return props.name();
  }

  @Override
  public java.util.List<String> path() {
    return props.path();
  }

  @Override
  public String delimiter() {
    return props.delimiter();
  }

  @Override
  public java.util.List<String> propertiesNames() {
    return props.propertiesNames();
  }

  @Override
  public boolean hasProperty(String name) {
    return props.hasProperty(name);
  }

  @Override
  public boolean hasValue(String name) {
    return props.hasValue(name);
  }

  @Override
  public @Nullable Object getValue(String name) {
    return props.getValue(name);
  }

  @Override
  public @Nullable String getString(String name) {
    return props.getString(name);
  }

  @Override
  public java.util.List<TraversablePropertiesSet> subProperties() {
    return props.subProperties();
  }

  @Override
  public boolean hasTraversePath(String... path) {
    return props.hasTraversePath(path);
  }

  @Override
  public boolean traverseTargetIsSpecified(String name) {
    return props.traverseTargetIsSpecified(name);
  }

  @Override
  public boolean traverseTargetIsString(String... path) {
    return props.traverseTargetIsString(path);
  }

  @Override
  public boolean traverseTargetIsStringCompatible(String... path) {
    return props.traverseTargetIsStringCompatible(path);
  }

  @Override
  public boolean traverseTargetIsProperties(String... path) {
    return props.traverseTargetIsProperties(path);
  }

  @Override
  public boolean traverseTargetIsPropertiesCompatible(String... path) {
    return props.traverseTargetIsPropertiesCompatible(path);
  }

  @Override
  public @Nullable Object traverse(String... path) {
    return props.traverse(path);
  }

  @Override
  public @Nullable Integer traverseToInteger(String... path) {
    return props.traverseToInteger(path);
  }

  @Override
  public @Nullable Integer traverseAndCastToInteger(String... path) {
    return props.traverseAndCastToInteger(path);
  }

  @Override
  public @Nullable Double traverseToDouble(String... path) {
    return props.traverseToDouble(path);
  }

  @Override
  public @Nullable Double traverseAndCastToDouble(String... path) {
    return props.traverseAndCastToDouble(path);
  }

  @Override
  public @Nullable String traverseToString(String... path) {
    return props.traverseToString(path);
  }

  @Override
  public @Nullable String traverseToString(String[] headPath, String... tailPath) {
    return props.traverseToString(headPath, tailPath);
  }

  @Override
  public String traverseToStringOrThrow(String... path) {
    return props.traverseToStringOrThrow(path);
  }

  @Override
  public @Nullable String traverseAndCastToString(String... path) {
    return props.traverseAndCastToString(path);
  }

  @Override
  public @Nullable TraversablePropertiesSet traverseToProperties(String... path) {
    return props.traverseToProperties(path);
  }

  @Override
  public TraversablePropertiesSet traverseToPropertiesOrThrow(String... path) {
    return props.traverseToPropertiesOrThrow(path);
  }

  @Override
  public @Nullable TraversablePropertiesSet traverseAndCastToProperties(String... path) {
    return props.traverseAndCastToProperties(path);
  }

  @Override
  public java.util.@Nullable List<Integer> traverseToIntegerList(String... path) {
    return props.traverseToIntegerList(path);
  }

  @Override
  public java.util.@Nullable List<Integer> traverseAndCastToIntegerList(String... path) {
    return props.traverseAndCastToIntegerList(path);
  }

  @Override
  public java.util.@Nullable List<Double> traverseToDoubleList(String... path) {
    return props.traverseToDoubleList(path);
  }

  @Override
  public java.util.@Nullable List<Double> traverseAndCastToDoubleList(String... path) {
    return props.traverseAndCastToDoubleList(path);
  }

  @Override
  public java.util.@Nullable List<String> traverseToStringList(String... path) {
    return props.traverseToStringList(path);
  }

  @Override
  public java.util.@Nullable List<String> traverseAndCastToStringList(String... path) {
    return props.traverseAndCastToStringList(path);
  }

  @Override
  public java.util.@Nullable List<TraversablePropertiesSet> traverseToPropertiesList(String... path) {
    return props.traverseToPropertiesList(path);
  }

  @Override
  public java.util.@Nullable List<TraversablePropertiesSet> traverseToPropertiesList(
      String[] headPath, String... tailPath
  ) {
    return props.traverseToPropertiesList(headPath, tailPath);
  }

  @Override
  public java.util.@Nullable List<TraversablePropertiesSet> traverseAndCastToPropertiesList(String... path) {
    return props.traverseAndCastToPropertiesList(path);
  }

  @SuppressWarnings("unchecked, rawtypes")
  private Object convertValue(Object value) {
    if (value == null) {
      return null;
    } else if (value instanceof TraversablePropertiesSet props) {
      return new CommonPropertiesSetReflectionWrapper(props);
    } else if (value instanceof tech.intellispaces.commons.properties.PropertiesSet props) {
      return new CommonPropertiesSetReflectionWrapper(props);
    } else if (value instanceof java.util.List<?> list) {
      if (list.isEmpty()) {
        return Lists.empty();
      }
      Class<?> elementClass = defineListElementClass(list);
      if (elementClass == PropertiesSet.class || elementClass == List.class) {
        var newList = new ArrayList<PropertiesSet>(list.size());
        for (Object e : list) {
          newList.add((PropertiesSet) convertValue(e));
        }
        return Lists.reflectionOf(newList, PropertiesSet.class);
      } else {
        return Lists.reflectionOf((java.util.List) list, (Class) elementClass);
      }
    }
    return value;
  }

  private Class<?> defineListElementClass(java.util.List<?> list) {
    Class<?> elementClass = Object.class;
    for (Object e : list) {
      if (e instanceof String) {
        elementClass = String.class;
      } else if (e instanceof Integer) {
        elementClass = Integer.class;
      } else if (e instanceof Double) {
        elementClass = Double.class;
      } else if (e instanceof tech.intellispaces.commons.properties.PropertiesSet) {
        elementClass = PropertiesSet.class;
      } else if (e instanceof java.util.List<?>) {
        elementClass = java.util.List.class;
      } else {
        throw NotImplementedExceptions.withCode("m126hA");
      }
    }
    return elementClass;
  }

  private String[] splitPath(String path) {
    return StringFunctions.split(path, props.delimiter()).toArray(new String[0]);
  }
}
