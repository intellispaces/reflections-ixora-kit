package tech.intellispaces.ixora.data.association;

import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;

import tech.intellispaces.commons.exception.UnexpectedExceptions;
import tech.intellispaces.commons.function.FunctionFunctions;
import tech.intellispaces.commons.type.ClassFunctions;
import tech.intellispaces.commons.type.Type;
import tech.intellispaces.commons.type.Types;
import tech.intellispaces.reflections.framework.annotation.Guide;
import tech.intellispaces.reflections.framework.annotation.Mapper;
import tech.intellispaces.reflections.framework.dataset.DatasetFunctions;
import tech.intellispaces.reflections.framework.naming.NameConventionFunctions;
import tech.intellispaces.reflections.framework.reflection.ReflectionFunctions;
import tech.intellispaces.reflections.framework.space.domain.DomainFunctions;

@Guide
public class SimplePropertiesSetToDataGuide implements PropertiesSetToDataGuide {

  @Mapper
  @Override
  public <D> D propertiesSetToData(PropertiesSet props, Type<D> dataType) {
    if (DatasetFunctions.isDatasetReflection(dataType.asClassType().baseClass())) {
      return process(props, dataType);
    }
    throw new UnsupportedOperationException("Not implemented");
  }

  @SuppressWarnings("unchecked")
  private <D> D process(PropertiesSet properties, Type<D> dataType) {
    Class<?> domainClass = ReflectionFunctions.getReflectionDomainClass(dataType.asClassType().baseClass());
    String dataReflectionCanonicalName = NameConventionFunctions.getUnmovableDatasetClassName(domainClass.getName());
    Class<?> dataReflectionClass = ClassFunctions.getClassOrElseThrow(dataReflectionCanonicalName, () ->
        UnexpectedExceptions.withMessage("Can't find data reflection class. Domain class {0}, " +
                "expected data reflection class {1}", domainClass.getCanonicalName(), dataReflectionCanonicalName));
    Constructor<?>[] constructors = dataReflectionClass.getDeclaredConstructors();
    if (constructors.length != 1) {
      throw UnexpectedExceptions.withMessage("Data class {0} must contain one constructor",
          dataReflectionCanonicalName);
    }
    int projectionChannelCount = DomainFunctions.getDomainProjectionChannelsExcludeConversionMethodsCount(
        domainClass);
    Constructor<?> constructor = constructors[0];
    if (constructor.getParameterCount() != projectionChannelCount) {
      throw UnexpectedExceptions.withMessage("Data class {0} must contain constructor with {1} parameters",
          dataReflectionCanonicalName, projectionChannelCount);
    }

    Object[] arguments = new Object[constructor.getParameterCount()];
    int index = 0;
    for (Parameter param : constructor.getParameters()) {
      Object value = properties.value(param.getName());
      if (value == null && param.getType().isPrimitive()) {
        value = ClassFunctions.getDefaultValueOf(param.getType());
      }
      if (value instanceof PropertiesSet && ReflectionFunctions.isReflectionClass(param.getType())) {
        value = process((PropertiesSet) value, Types.get(param.getType()));
      }
      arguments[index++] = value;
    }
    return (D) FunctionFunctions.applyAndWrap(arguments, constructor::newInstance);
  }
}
