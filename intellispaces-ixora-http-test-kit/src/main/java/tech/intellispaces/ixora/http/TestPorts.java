package tech.intellispaces.ixora.http;

import tech.intellispaces.reflections.object.reference.DownwardObjectFactory;

public interface TestPorts {

  static MovableTestPortHandle create(
      DownwardObjectFactory<? extends MovableInboundHttpPort> underlyingPortHandleFactory
  ) {
    return new TestPortImplWrapper(underlyingPortHandleFactory);
  }
}
