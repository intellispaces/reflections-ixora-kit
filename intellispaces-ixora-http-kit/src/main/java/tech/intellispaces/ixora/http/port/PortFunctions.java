package tech.intellispaces.ixora.http.port;

import tech.intellispaces.commons.exception.UnexpectedExceptions;
import tech.intellispaces.commons.type.ClassFunctions;
import tech.intellispaces.commons.java.reflection.customtype.CustomType;
import tech.intellispaces.commons.java.reflection.method.MethodStatement;
import tech.intellispaces.ixora.http.common.HttpNameConventionFunctions;
import tech.intellispaces.jaquarius.channel.Channel1;

import java.util.Optional;

public interface PortFunctions {

  @SuppressWarnings("unchecked")
  static Class<? extends Channel1> getChannelClass(
      CustomType portDomain, CustomType ontologyType, MethodStatement channelMethod
  ) {
    String channelClassCanonicalName = HttpNameConventionFunctions.getActualPortExchangeChannelCanonicalName(
        portDomain, ontologyType, channelMethod
    );
    Optional<Class<?>> channelClass = ClassFunctions.getClass(channelClassCanonicalName);
    if (channelClass.isEmpty()) {
      throw UnexpectedExceptions.withMessage("Could not find channel class by name {0}",
          channelClassCanonicalName);
    }
    return (Class<? extends Channel1>) channelClass.get();
  }
}
