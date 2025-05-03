package tech.intellispaces.ixora.http;

import tech.intellispaces.reflections.annotation.Factory;

@Factory
public class DedicatedHttpPortFactory implements DedicatedHttpPortAssistantCustomizer {

  @Override
  public MovableDedicatedHttpPortHandle create(String baseUrl, MovableHttpPort httpPort) {
    return new DedicatedHttpPortImplWrapper(baseUrl, httpPort);
  }
}
