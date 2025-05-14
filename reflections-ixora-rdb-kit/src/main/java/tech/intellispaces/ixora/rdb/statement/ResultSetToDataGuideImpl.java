package tech.intellispaces.ixora.rdb.statement;

import jakarta.persistence.Column;
import tech.intellispaces.commons.collection.ArraysFunctions;
import tech.intellispaces.commons.exception.UnexpectedExceptions;
import tech.intellispaces.commons.text.StringFunctions;
import tech.intellispaces.commons.type.ClassFunctions;
import tech.intellispaces.commons.type.Type;
import tech.intellispaces.ixora.data.collection.List;
import tech.intellispaces.ixora.data.collection.Lists;
import tech.intellispaces.reflections.framework.annotation.*;
import tech.intellispaces.reflections.framework.dataset.DatasetFunctions;
import tech.intellispaces.reflections.framework.naming.NameConventionFunctions;
import tech.intellispaces.reflections.framework.reflection.ReflectionFunctions;

import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Guide
public class ResultSetToDataGuideImpl {

  @Mapper(ResultSetToDataChannel.class)
  @SuppressWarnings("unchecked")
  public <D> D resultSetToData(ResultSet resultSet, Type<D> dataType) {
    var dataClass = (Class<D>) dataType.asClassType().baseClass();
    Class<?> domainClass = getDomainClass(dataClass);
    Constructor<D> constructor = getDataReflectionConstructor(dataClass, domainClass);
    Object[] arguments = makeDataReflectionArguments(resultSet, dataClass, domainClass, constructor);
    try {
      return constructor.newInstance(arguments);
    } catch (Exception e) {
      throw UnexpectedExceptions.withCauseAndMessage(e, "Failed to create data handle");
    }
  }

  @MapperOfMoving(ResultSetToDataListChannel.class)
  @SuppressWarnings("unchecked")
  public <D> List<D> resultSetToDataList(ResultSet resultSet, Type<D> dataType) {
    var dataClass = (Class<D>) dataType.asClassType().baseClass();
    Class<?> domainClass = getDomainClass(dataClass);
    Constructor<D> constructor = getDataReflectionConstructor(dataClass, domainClass);
    java.util.List<D> values = new ArrayList<>();
    while (((MovableResultSet) resultSet).next()) {
      Object[] arguments = makeDataReflectionArguments(resultSet, dataClass, domainClass, constructor);
      try {
        values.add(constructor.newInstance(arguments));
      } catch (Exception e) {
        throw UnexpectedExceptions.withCauseAndMessage(e, "Failed to create data handle");
      }
    }
    return Lists.reflectionOf(values, dataClass);
  }

  @SuppressWarnings("unchecked")
  private <D> Constructor<D> getDataReflectionConstructor(Class<D> dataClass, Class<?> domainClass) {
    String dataReflectionClassName = NameConventionFunctions.getUnmovableDatasetClassName(domainClass.getCanonicalName());
    Class<D> dataReflectionClass = (Class<D>) ClassFunctions.getClass(dataReflectionClassName).orElseThrow(() ->
        UnexpectedExceptions.withMessage("Could not find data handle class by name {0} ",
            dataReflectionClassName)
    );

    Constructor<?>[] constructors = dataReflectionClass.getConstructors();
    if (constructors.length > 1) {
      throw UnexpectedExceptions.withMessage("Data handle class should have one constructor");
    }
    return (Constructor<D>) constructors[0];
  }

  private <D> Class<?> getDomainClass(Class<D> dataClass) {
    Class<?> domainClass = ReflectionFunctions.getDomainClassOfObjectHandle(dataClass);
    if (!DatasetFunctions.isDatasetDomain(domainClass)) {
      throw UnexpectedExceptions.withMessage("Expected object handle class of the data domain. " +
          "Data domain should be annotated with @{0}", Dataset.class.getSimpleName());
    }
    return domainClass;
  }

  private <D> Object[] makeDataReflectionArguments(
      ResultSet resultSet, Class<D> dataClass, Class<?> domainClass, Constructor<D> constructor
  ) {
    Map<String, String> mapping = makeChannelAliasToColumnNameMapping(domainClass);

    Object[] arguments = new Object[constructor.getParameterCount()];
    for (int index = 0; index < constructor.getParameterCount(); index++) {
      Parameter param = constructor.getParameters()[index];
      Name name = param.getAnnotation(Name.class);
      if (name == null) {
        throw UnexpectedExceptions.withMessage("Parameter {0} of the data class {1} constructor " +
            "should be marked with annotation {2}", index, dataClass, Name.class.getSimpleName());
      }
      String alias = name.value();
      Class<?> paramClass = param.getType();
      setArgument(resultSet, arguments, index, mapping.get(alias), paramClass);
    }
    return arguments;
  }

  private void setArgument(
      ResultSet resultSet, Object[] arguments, int index, String columnName, Class<?> paramClass
  ) {
    if (paramClass == int.class) {
      Integer value = resultSet.integer32Value(columnName);
      if (value == null) {
        throw UnexpectedExceptions.withMessage("Null value of the primitive integer value by name {0}",
            columnName);
      }
      arguments[index] = value;
    } else if (paramClass == Integer.class) {
      arguments[index] = resultSet.integer32Value(columnName);
    } else if (paramClass == String.class) {
      arguments[index] = resultSet.stringValue(columnName);
    }
  }

  private Map<String, String> makeChannelAliasToColumnNameMapping(Class<?> domainClass) {
    Map<String, String> mapping = new HashMap<>();
    ArraysFunctions.foreach(domainClass.getDeclaredMethods(), method -> {
      Column column = method.getAnnotation(Column.class);
      if (column == null) {
        throw UnexpectedExceptions.withMessage("Method {0} of the class {1} should be marked with annotation {2}",
            method.getName(), domainClass.getCanonicalName(), Column.class.getSimpleName()
        );
      }
      if (StringFunctions.isNullOrBlank(column.name())) {
        throw UnexpectedExceptions.withMessage("Name attribute should be defined in annotation {0} " +
                "on the method {1} in class {2}",
            Column.class.getSimpleName(), method.getName(), domainClass.getCanonicalName()
        );
      }
      mapping.put(method.getName(), column.name());
    });
    return mapping;
  }
}
