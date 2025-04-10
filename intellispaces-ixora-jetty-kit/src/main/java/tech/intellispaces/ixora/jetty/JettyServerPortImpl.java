package tech.intellispaces.ixora.jetty;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import tech.intellispaces.ixora.http.HttpPortExchangeChannel;
import tech.intellispaces.ixora.http.InboundHttpPortDomain;
import tech.intellispaces.jaquarius.annotation.Mapper;
import tech.intellispaces.jaquarius.annotation.Mover;
import tech.intellispaces.jaquarius.annotation.ObjectHandle;
import tech.intellispaces.jaquarius.exception.TraverseExceptions;
import tech.intellispaces.jaquarius.object.reference.MovableObjectHandle;
import tech.intellispaces.jaquarius.space.channel.ChannelFunctions;

@ObjectHandle(JettyServerPortDomain.class)
public abstract class JettyServerPortImpl implements MovableJettyServerPort, MovableJettyServerPortHandle {
  private final int portNumber;
  private final Class<? extends HttpPortExchangeChannel> exchangeChannel;
  private final Server server;
  private final JettyServlet servlet;

  public JettyServerPortImpl(
      int portNumber,
      Class<? extends HttpPortExchangeChannel> exchangeChannel
  ) {
    this.portNumber = portNumber;
    this.exchangeChannel = exchangeChannel;

    this.server = new Server();

    var connector = new ServerConnector(server);
    connector.setPort(portNumber);
    server.setConnectors(new Connector[] { connector });

    ServletHandler servletHandler = new ServletHandler();
    server.setHandler(servletHandler);

    servlet = new JettyServlet();
    ServletHolder servletHolder = new ServletHolder(servlet);
    servletHandler.addServletWithMapping(servletHolder, "/");
  }

  @Mover
  @Override
  public MovableJettyServerPortHandle open() {
    try {
      MovableObjectHandle<?> logicalPort = getLogicalPort();
      if (logicalPort == null) {
        throw TraverseExceptions.withMessage("Could not define logical port");
      }
      servlet.init(logicalPort, exchangeChannel);

      server.start();
    } catch (Exception e) {
      throw TraverseExceptions.withCauseAndMessage(e, "Could not open HTTP server");
    }
    return this;
  }

  @Mover
  @Override
  public MovableJettyServerPortHandle close() {
    stopServer();
    return this;
  }

  @Override
  public void unbind() {
    stopServer();
  }

  private void stopServer() {
    try {
      server.stop();
    } catch (Exception e) {
      throw TraverseExceptions.withCauseAndMessage(e, "Could not close HTTP server");
    }
  }

  @Mapper
  @Override
  public int portNumber() {
    return portNumber;
  }

  private MovableObjectHandle<?> getLogicalPort() {
    Class<? extends InboundHttpPortDomain> logicalPortDomain = getLogicalPortDomain();
    return mapTo(logicalPortDomain);
  }

  @SuppressWarnings("unchecked")
  private Class<? extends InboundHttpPortDomain> getLogicalPortDomain() {
    return (Class<? extends InboundHttpPortDomain>) ChannelFunctions.getChannelSourceDomainClass(exchangeChannel);
  }
}
