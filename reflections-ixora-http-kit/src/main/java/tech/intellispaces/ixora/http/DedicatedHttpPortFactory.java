package tech.intellispaces.ixora.http;

import tech.intellispaces.reflections.framework.annotation.Factory;

@Factory
public class DedicatedHttpPortFactory implements DedicatedHttpPortAssistantCustomizer {

  @Override
  public MovableDedicatedHttpPortReflection create(String baseUrl, MovableHttpPort httpPort) {
    return new DedicatedHttpPortImplWrapper(baseUrl, httpPort);
  }
}
