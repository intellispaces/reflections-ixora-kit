package tech.intellispaces.ixora.http;

import tech.intellispaces.reflections.framework.reflection.DownwardObjectFactory;

public interface TestPorts {

  static MovableTestPortReflection create(
      DownwardObjectFactory<? extends MovableInboundHttpPort> underlyingPortHandleFactory
  ) {
    return new TestPortImplWrapper(underlyingPortHandleFactory);
  }
}
