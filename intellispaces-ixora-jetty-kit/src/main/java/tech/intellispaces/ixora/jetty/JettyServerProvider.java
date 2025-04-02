package tech.intellispaces.ixora.jetty;

import tech.intellispaces.ixora.http.HttpPortExchangeChannel;
import tech.intellispaces.jaquarius.annotation.ObjectProvider;

@ObjectProvider
public class JettyServerProvider implements JettyServerPortProviderCustomizer {

  @Override
  public MovableJettyServerPortHandle create(
      int portNumber, Class<? extends HttpPortExchangeChannel> exchangeChannel
  ) {
    return new JettyServerPortImplWrapper(portNumber, exchangeChannel);
  }
}
