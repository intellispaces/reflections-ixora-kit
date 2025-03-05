package tech.intellispaces.ixora.http;

public interface TestPorts {

  static MovableTestPort get(MovableInboundHttpPort operativePort) {
    return new TestPortImplWrapper(operativePort);
  }
}
