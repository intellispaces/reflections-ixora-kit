package tech.intellispaces.ixora.jetty;

import tech.intellispaces.jaquarius.annotation.Factory;
import tech.intellispaces.jaquarius.object.reference.MovableObjectHandle;

@Factory
public class JettyServerFactory implements JettyServerPortAssistantCustomizer {

  @Override
  public MovableJettyServerPortHandle create(int portNumber, MovableObjectHandle<?> overlyingHandle) {
    return new JettyServerPortImplWrapper(portNumber, overlyingHandle);
  }
}
