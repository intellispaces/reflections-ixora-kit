package tech.intellispaces.jaquarius.ixora.jetty;

import tech.intellispaces.jaquarius.ixora.http.HttpPortExchangeChannel;

public interface JettyServerPorts {

  static MovableJettyServerPortHandle get(
      int portNumber,
      Class<? extends HttpPortExchangeChannel> exchangeChannel
  ) {
    return new JettyServerPortHandleImplWrapper(portNumber, exchangeChannel);
  }
}
