package tech.intellispaces.ixora.http;

import tech.intellispaces.reflections.annotation.Mover;
import tech.intellispaces.reflections.annotation.ObjectHandle;
import tech.intellispaces.reflections.object.reference.DownwardObjectFactory;

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
