package tech.intellispaces.ixora.rdb.annotationprocessor;

import jakarta.persistence.Id;
import tech.intellispaces.commons.exception.UnexpectedExceptions;
import tech.intellispaces.commons.stream.Collectors;
import tech.intellispaces.commons.text.StringFunctions;
import tech.intellispaces.commons.type.ClassNameFunctions;
import tech.intellispaces.jstatements.customtype.CustomType;
import tech.intellispaces.jstatements.method.MethodStatement;
import tech.intellispaces.jstatements.reference.TypeReference;
import tech.intellispaces.reflections.framework.reflection.ReflectionFunctions;

import java.util.Optional;
import java.util.function.Function;

public interface EntityAnnotationFunctions {

  static String getEntityReflectionCanonicalName(CustomType entityType) {
    return ReflectionFunctions.getGeneralRegularObjectTypename(entityType);
  }

  static String getCrudOntologyCanonicalName(CustomType entityType) {
    return StringFunctions.replaceTailOrElseThrow(entityType.canonicalName(), "Domain", "CrudOntology");
  }

  static String getCrudOntologySimpleName(CustomType entityType) {
    return ClassNameFunctions.getSimpleName(getCrudOntologyCanonicalName(entityType));
  }

  static String getCrudGuideCanonicalName(CustomType entityType) {
    return StringFunctions.replaceTailOrElseThrow(entityType.canonicalName(), "Domain", "CrudGuide");
  }

  static String getCrudGuideGeneratedImplCanonicalName(CustomType entityType) {
    return ClassNameFunctions.addPrefixToSimpleName(
        "Default", StringFunctions.replaceTailOrElseThrow(entityType.canonicalName(), "Domain", "CrudGuide")
    );
  }

  static Optional<MethodStatement> findIdentifierMethod(CustomType entityType) {
    return entityType.declaredMethods().stream()
        .filter(m -> m.hasAnnotation(Id.class))
        .collect(Collectors.optional());
  }

  static String getIdentifierType(CustomType entityType, MethodStatement identifierMethod) {
    TypeReference returnType = identifierMethod.returnType()
        .orElseThrow(() -> UnexpectedExceptions.withMessage(
            "Entity identifier method {0} of the entity {1} should return value",
            identifierMethod.name(), entityType.canonicalName()
        ));
    return ReflectionFunctions.getGeneralRegularObjectTypename(returnType, Function.identity());
  }

  static String getTransactionToEntityByIdentifierChannelSimpleName(CustomType entityType) {
    return "TransactionTo" +
        StringFunctions.capitalizeFirstLetter(StringFunctions.removeTailOrElseThrow(entityType.simpleName(), "Domain")) +
        "ByIdentifierChannel";
  }

  static String getTransactionToNewEntityChannelSimpleName(CustomType entityType) {
    return "TransactionToNew" +
        StringFunctions.capitalizeFirstLetter(StringFunctions.removeTailOrElseThrow(entityType.simpleName(), "Domain")) +
        "Channel";
  }

  static String getTransactionToEntityByIdentifierGuideSimpleName(CustomType entityType) {
    return "TransactionTo" +
        StringFunctions.capitalizeFirstLetter(StringFunctions.removeTailOrElseThrow(entityType.simpleName(), "Domain")) +
        "ByIdentifierGuide";
  }
}
