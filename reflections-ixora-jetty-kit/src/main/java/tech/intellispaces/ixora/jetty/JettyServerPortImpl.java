package tech.intellispaces.ixora.jetty;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import tech.intellispaces.reflections.framework.annotation.Mapper;
import tech.intellispaces.reflections.framework.annotation.Mover;
import tech.intellispaces.reflections.framework.annotation.Reflection;
import tech.intellispaces.reflections.framework.exception.TraverseExceptions;
import tech.intellispaces.reflections.framework.reflection.MovableReflection;
import tech.intellispaces.reflections.framework.reflection.OverlyingHandleController;
import tech.intellispaces.reflections.framework.reflection.PostRegistrationHandleProcessor;

@Reflection(JettyServerPortDomain.class)
public abstract class JettyServerPortImpl implements MovableJettyServerPortReflection, OverlyingHandleController, PostRegistrationHandleProcessor {
  private final int portNumber;
  private final Server server;
  private final JettyServlet servlet;
  private MovableReflection<?> overlyingHandle;

  public JettyServerPortImpl(int portNumber, MovableReflection<?> overlyingHandle) {
    this.portNumber = portNumber;
    this.overlyingHandle = overlyingHandle;

    this.server = new Server();

    var connector = new ServerConnector(server);
    connector.setPort(portNumber);
    server.setConnectors(new Connector[] { connector });

    var servletHandler = new ServletHandler();
    server.setHandler(servletHandler);

    servlet = new JettyServlet();
    var servletHolder = new ServletHolder(servlet);
    servletHandler.addServletWithMapping(servletHolder, "/");
  }

  @Override
  public void postRegistration() {
    setOverlyingHandle(overlyingHandle);
  }

  @Mapper
  @Override
  public int portNumber() {
    return portNumber;
  }

  @Mover
  @Override
  public MovableJettyServerPortReflection open() {
    try {
      servlet.init(this);
      server.start();
    } catch (Exception e) {
      throw TraverseExceptions.withCauseAndMessage(e, "Could not open Jetty HTTP server");
    }
    return this;
  }

  @Mover
  @Override
  public MovableJettyServerPortReflection shut() {
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
}
