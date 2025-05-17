package tech.intellispaces.ixora.http;

import tech.intellispaces.reflections.framework.reflection.DownwardObjectFactory;

public interface TestPorts {

  static MovableTestPortReflection create(
      DownwardObjectFactory<? extends MovableInboundHttpPort> underlyingPortReflectionFactory
  ) {
    return new TestPortImplWrapper(underlyingPortReflectionFactory);
  }
}
