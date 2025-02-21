package tech.intellispaces.ixora.jetty;

import tech.intellispaces.ixora.http.HttpPortExchangeChannel;

public interface JettyServerPorts {

  static MovableJettyServerPortHandle get(
      int portNumber,
      Class<? extends HttpPortExchangeChannel> exchangeChannel
  ) {
    return new JettyServerPortHandleImplWrapper(portNumber, exchangeChannel);
  }
}
