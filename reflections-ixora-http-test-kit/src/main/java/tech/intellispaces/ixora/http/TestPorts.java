package tech.intellispaces.ixora.http;

import tech.intellispaces.reflections.framework.reflection.DownwardObjectFactory;

public interface TestPorts {

  static MovableTestPort create(
      DownwardObjectFactory<? extends MovableInboundHttpPort> underlyingPortReflectionFactory
  ) {
    return new TestPortReflectionWrapper(underlyingPortReflectionFactory);
  }
}
