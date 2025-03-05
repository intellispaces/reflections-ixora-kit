package tech.intellispaces.ixora.http;

import tech.intellispaces.jaquarius.annotation.MapperOfMoving;
import tech.intellispaces.jaquarius.annotation.Mover;
import tech.intellispaces.jaquarius.annotation.ObjectHandle;

@ObjectHandle(TestPortDomain.class)
public abstract class TestPortImpl implements MovableTestPort {
  private final MovableInboundHttpPort operativePort;

  public TestPortImpl(MovableInboundHttpPort operativePort) {
    this.operativePort = operativePort;
  }

  public MovableInboundHttpPort getOperativePort() {
    return operativePort;
  }

  @Mover
  @Override
  public MovableTestPort open() {
    operativePort.open();
    return this;
  }

  @Mover
  @Override
  public MovableTestPort close() {
    operativePort.close();
    return this;
  }

  @Override
  @MapperOfMoving
  public HttpResponse exchange(HttpRequest request) {
    return operativePort.exchange(request);
  }
}
