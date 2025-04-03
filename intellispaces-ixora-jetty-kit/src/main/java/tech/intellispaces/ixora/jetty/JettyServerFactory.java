package tech.intellispaces.ixora.jetty;

import tech.intellispaces.ixora.http.HttpPortExchangeChannel;
import tech.intellispaces.jaquarius.annotation.ObjectFactory;

@ObjectFactory
public class JettyServerFactory implements JettyServerPortAssistantExtension {

  @Override
  public MovableJettyServerPortHandle create(
      int portNumber, Class<? extends HttpPortExchangeChannel> exchangeChannel
  ) {
    return new JettyServerPortImplWrapper(portNumber, exchangeChannel);
  }
}
