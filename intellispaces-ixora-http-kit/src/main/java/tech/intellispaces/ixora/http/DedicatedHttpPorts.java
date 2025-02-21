package tech.intellispaces.ixora.http;

public interface DedicatedHttpPorts {

  static MovableDedicatedHttpPortHandle get(String baseUrl, MovableHttpPortHandle httpPort) {
    return new DedicatedHttpPortHandleImplWrapper(baseUrl, httpPort);
  }
}
