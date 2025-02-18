package tech.intellispaces.jaquarius.ixora.http;

import tech.intellispaces.jaquarius.annotation.MapperOfMoving;
import tech.intellispaces.jaquarius.annotation.Mover;
import tech.intellispaces.jaquarius.annotation.ObjectHandle;

@ObjectHandle(TestPortDomain.class)
public abstract class TestPortHandleImpl implements MovableTestPortHandle {
  private final MovableInboundHttpPortHandle operativePort;

  public TestPortHandleImpl(MovableInboundHttpPortHandle operativePort) {
    this.operativePort = operativePort;
  }

  public MovableInboundHttpPortHandle getOperativePort() {
    return operativePort;
  }

  @Mover
  @Override
  public MovableTestPortHandle open() {
    operativePort.open();
    return this;
  }

  @Mover
  @Override
  public MovableTestPortHandle close() {
    operativePort.close();
    return this;
  }

  @Override
  @MapperOfMoving
  public HttpResponseHandle exchange(HttpRequestHandle request) {
    return operativePort.exchange(request);
  }
}
