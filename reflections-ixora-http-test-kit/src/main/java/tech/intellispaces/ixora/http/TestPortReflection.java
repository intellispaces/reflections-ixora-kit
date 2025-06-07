package tech.intellispaces.ixora.http;

import tech.intellispaces.reflections.framework.annotation.Mover;
import tech.intellispaces.reflections.framework.annotation.Reflection;
import tech.intellispaces.reflections.framework.reflection.DownwardObjectFactory;

@Reflection(domainClass = TestPortDomain.class)
public abstract class TestPortReflection implements MovableTestPort {
  private final MovableInboundHttpPort underlyingPort;

  public TestPortReflection(DownwardObjectFactory<? extends MovableInboundHttpPort> underlyingPortReflectionFactory) {
    this.underlyingPort = underlyingPortReflectionFactory.create(this);
  }

  @Mover
  @Override
  public MovableTestPort open() {
    underlyingPort.open();
    return this;
  }

  @Mover
  @Override
  public MovableTestPort shut() {
    underlyingPort.shut();
    return this;
  }
}
