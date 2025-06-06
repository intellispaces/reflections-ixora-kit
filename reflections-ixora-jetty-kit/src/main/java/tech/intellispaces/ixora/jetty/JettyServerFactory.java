package tech.intellispaces.ixora.jetty;

import tech.intellispaces.reflections.framework.annotation.Factory;
import tech.intellispaces.reflections.framework.reflection.MovableReflection;

@Factory
public class JettyServerFactory implements JettyServerPortAssistantCustomizer {

  @Override
  public MovableJettyServerPort create(int portNumber, MovableReflection overlyingReflection) {
    return new JettyServerPortReflectionWrapper(portNumber, overlyingReflection);
  }
}
