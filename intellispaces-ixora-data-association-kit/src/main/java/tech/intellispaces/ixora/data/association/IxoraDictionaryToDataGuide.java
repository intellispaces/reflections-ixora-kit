package tech.intellispaces.ixora.data.association;

import tech.intellispaces.commons.base.exception.UnexpectedExceptions;
import tech.intellispaces.commons.base.function.FunctionFunctions;
import tech.intellispaces.commons.base.type.ClassFunctions;
import tech.intellispaces.commons.base.type.Type;
import tech.intellispaces.commons.base.type.Types;
import tech.intellispaces.jaquarius.annotation.Guide;
import tech.intellispaces.jaquarius.annotation.Mapper;
import tech.intellispaces.jaquarius.dataset.DatasetFunctions;
import tech.intellispaces.ixora.data.dictionary.DictionaryHandle;
import tech.intellispaces.ixora.data.dictionary.DictionaryToDataGuide;
import tech.intellispaces.jaquarius.naming.NameConventionFunctions;
import tech.intellispaces.jaquarius.object.reference.ObjectHandleFunctions;

import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;

@Guide
public class IxoraDictionaryToDataGuide implements DictionaryToDataGuide {

  @Mapper
  @Override
  public <D> D dictionaryToData(DictionaryHandle dictionary, Type<D> dataType) {
    if (DatasetFunctions.isDatasetObjectHandle(dataType.asClassType().baseClass())) {
      return process(dictionary, dataType);
    }
    throw new UnsupportedOperationException("Not implemented");
  }

  @SuppressWarnings("unchecked")
  private <D> D process(DictionaryHandle dictionary, Type<D> dataType) {
    Class<?> domainClass = ObjectHandleFunctions.getDomainClassOfObjectHandle(dataType.asClassType().baseClass());
    String dataHandleObjectCanonicalName = NameConventionFunctions.getDatasetClassName(domainClass.getName());
    Class<?> dataHandleObjectClass = ClassFunctions.getClassOrElseThrow(dataHandleObjectCanonicalName, () ->
        UnexpectedExceptions.withMessage("Can't find data handle class. Domain class {0}, " +
                "expected data handle class {1}", domainClass.getCanonicalName(), dataHandleObjectCanonicalName));
    Constructor<?>[] constructors = dataHandleObjectClass.getDeclaredConstructors();
    if (constructors.length != 1) {
      throw UnexpectedExceptions.withMessage("Data class {0} must contain one constructor",
          dataHandleObjectCanonicalName);
    }
    Constructor<?> constructor = constructors[0];
    if (constructor.getParameterCount() != domainClass.getMethods().length) {
      throw UnexpectedExceptions.withMessage("Data class {0} must contain constructor with {1} parameters",
          dataHandleObjectCanonicalName, dataType.asClassType().baseClass().getMethods().length);
    }

    Object[] arguments = new Object[constructor.getParameterCount()];
    int index = 0;
    for (Parameter param : constructor.getParameters()) {
      Object value = dictionary.value(param.getName());
      if (value == null && param.getType().isPrimitive()) {
        value = ClassFunctions.getDefaultValueOf(param.getType());
      }
      if (value instanceof DictionaryHandle && ObjectHandleFunctions.isObjectHandleClass(param.getType())) {
        value = process((DictionaryHandle) value, Types.get(param.getType()));
      }
      arguments[index++] = value;
    }
    return (D) FunctionFunctions.applyAndWrap(arguments, constructor::newInstance);
  }
}
