package tech.intellispaces.ixora.jetty;

import tech.intellispaces.jaquarius.annotation.ObjectFactory;
import tech.intellispaces.jaquarius.object.reference.MovableObjectHandle;

@ObjectFactory
public class JettyServerFactory implements JettyServerPortAssistantExtension {

  @Override
  public MovableJettyServerPortHandle create(int portNumber, MovableObjectHandle<?> overlyingHandle) {
    return new JettyServerPortImplWrapper(portNumber, overlyingHandle);
  }
}
