package tech.intellispaces.ixora.http;

import tech.intellispaces.jaquarius.annotation.Mover;
import tech.intellispaces.jaquarius.annotation.ObjectHandle;
import tech.intellispaces.jaquarius.object.reference.DownwardObjectFactory;

@ObjectHandle(TestPortDomain.class)
public abstract class TestPortImpl implements MovableTestPortHandle {
  private final MovableInboundHttpPort underlyingPort;

  public TestPortImpl(DownwardObjectFactory<? extends MovableInboundHttpPort> underlyingPortHandleFactory) {
    this.underlyingPort = underlyingPortHandleFactory.create(this);
  }

  @Mover
  @Override
  public MovableTestPortHandle open() {
    underlyingPort.open();
    return this;
  }

  @Mover
  @Override
  public MovableTestPortHandle shut() {
    underlyingPort.shut();
    return this;
  }
}
