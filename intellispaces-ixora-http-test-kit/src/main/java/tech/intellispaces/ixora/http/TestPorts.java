package tech.intellispaces.ixora.http;

public interface TestPorts {

  static MovableTestPortHandle get(MovableInboundHttpPortHandle operativePort) {
    return new TestPortHandleImplWrapper(operativePort);
  }
}
