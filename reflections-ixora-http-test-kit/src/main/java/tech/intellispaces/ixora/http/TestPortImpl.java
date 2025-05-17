package tech.intellispaces.ixora.http;

import tech.intellispaces.reflections.framework.annotation.Mover;
import tech.intellispaces.reflections.framework.annotation.Reflection;
import tech.intellispaces.reflections.framework.reflection.DownwardObjectFactory;

@Reflection(TestPortDomain.class)
public abstract class TestPortImpl implements MovableTestPortReflection {
  private final MovableInboundHttpPort underlyingPort;

  public TestPortImpl(DownwardObjectFactory<? extends MovableInboundHttpPort> underlyingPortReflectionFactory) {
    this.underlyingPort = underlyingPortReflectionFactory.create(this);
  }

  @Mover
  @Override
  public MovableTestPortReflection open() {
    underlyingPort.open();
    return this;
  }

  @Mover
  @Override
  public MovableTestPortReflection shut() {
    underlyingPort.shut();
    return this;
  }
}
