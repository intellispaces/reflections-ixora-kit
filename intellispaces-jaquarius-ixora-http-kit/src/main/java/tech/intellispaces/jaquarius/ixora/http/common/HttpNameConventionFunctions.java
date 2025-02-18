package tech.intellispaces.jaquarius.ixora.http.common;

import tech.intellispaces.commons.base.text.StringFunctions;
import tech.intellispaces.commons.base.type.ClassNameFunctions;
import tech.intellispaces.commons.java.reflection.customtype.CustomType;
import tech.intellispaces.commons.java.reflection.method.MethodParam;
import tech.intellispaces.commons.java.reflection.method.MethodStatement;
import tech.intellispaces.commons.java.reflection.reference.CustomTypeReferences;
import tech.intellispaces.jaquarius.ixora.http.HttpRequestDomain;
import tech.intellispaces.jaquarius.naming.NameConventionFunctions;

import java.util.List;

public interface HttpNameConventionFunctions {

  static String getPortHandleCanonicalName(CustomType httpPortDomain) {
    return StringFunctions.replaceTailOrElseThrow(httpPortDomain.canonicalName(), "Domain", "HandleGenerated");
  }

  static String getPortHandleImplCanonicalName(CustomType httpPortDomain) {
    return getPortHandleCanonicalName(httpPortDomain) + "Wrapper";
  }

  static String getPortProviderCanonicalName(CustomType httpPortDomain) {
    return StringFunctions.replaceTailOrElseThrow(httpPortDomain.canonicalName(), "Domain", "s");
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
