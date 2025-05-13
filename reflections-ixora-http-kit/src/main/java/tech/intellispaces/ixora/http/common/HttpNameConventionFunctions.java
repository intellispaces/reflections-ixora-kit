package tech.intellispaces.ixora.http.common;

import tech.intellispaces.commons.text.StringFunctions;
import tech.intellispaces.commons.type.ClassNameFunctions;
import tech.intellispaces.ixora.http.HttpRequestDomain;
import tech.intellispaces.jstatements.customtype.CustomType;
import tech.intellispaces.jstatements.method.MethodParam;
import tech.intellispaces.jstatements.method.MethodStatement;
import tech.intellispaces.jstatements.reference.CustomTypeReferences;
import tech.intellispaces.reflections.framework.naming.NameConventionFunctions;

import java.util.List;

public interface HttpNameConventionFunctions {

  static String getPortReflectionCanonicalName(CustomType httpPortDomain) {
    return StringFunctions.replaceTailOrElseThrow(httpPortDomain.canonicalName(), "Domain", "ReflectionGenerated");
  }

  static String getPortReflectionImplCanonicalName(CustomType httpPortDomain) {
    return getPortReflectionCanonicalName(httpPortDomain) + "Wrapper";
  }

  static String getPortAssistantCustomizerCanonicalName(CustomType httpPortDomain) {
    return StringFunctions.replaceTailOrElseThrow(httpPortDomain.canonicalName(), "Domain", "AssistantCustomizer");
  }

  static String getPortFactoryCanonicalName(CustomType httpPortDomain) {
    return StringFunctions.replaceTailOrElseThrow(httpPortDomain.canonicalName(), "Domain", "Factory");
  }

  static String getPortGuideCanonicalName(CustomType ontology) {
    return StringFunctions.replaceTailOrElseThrow(ontology.canonicalName(), "Ontology", "Guide");
  }

  static String getPortExchangeChannelCanonicalName(CustomType httpPortDomain) {
    MethodStatement exchangeChannel = httpPortDomain
        .declaredMethod("exchange", List.of(CustomTypeReferences.get(HttpRequestDomain.class)))
        .orElseThrow();
    return NameConventionFunctions.getChannelClassCanonicalName(exchangeChannel);
  }

  static String getActualPortExchangeChannelCanonicalName(
      CustomType portDomain, CustomType ontology, MethodStatement channelMethod
  ) {
    var sb = new StringBuilder();

    String packageName = ClassNameFunctions.getPackageName(ontology.canonicalName());
    if (StringFunctions.isNotBlank(packageName)) {
      sb.append(packageName).append(".");
    }
    sb.append(StringFunctions.removeTailOrElseThrow(portDomain.simpleName(), "Domain"));
    sb.append(StringFunctions.capitalizeFirstLetter(channelMethod.name()));
    for (MethodParam param : channelMethod.params()) {
      sb.append(StringFunctions.capitalizeFirstLetter(param.name()));
    }
    sb.append("ExchangeChannel");
    return sb.toString();
  }

  static String getFormalPortExchangeChannelCanonicalName(
      CustomType portDomain, CustomType ontology, MethodStatement channelMethod
  ) {
    var sb = new StringBuilder();
    sb.append(StringFunctions.removeTailOrElseThrow(portDomain.canonicalName(), "Domain"));
    sb.append(StringFunctions.capitalizeFirstLetter(channelMethod.name()));
    for (MethodParam param : channelMethod.params()) {
      sb.append(StringFunctions.capitalizeFirstLetter(param.name()));
    }
    sb.append("ExchangeChannel");
    return sb.toString();
  }
}
