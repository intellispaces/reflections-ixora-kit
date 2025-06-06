package tech.intellispaces.ixora.http;

import tech.intellispaces.reflections.framework.annotation.Factory;

@Factory
public class DedicatedHttpPortFactory implements DedicatedHttpPortAssistantCustomizer {

  @Override
  public MovableDedicatedHttpPort create(String baseUrl, MovableHttpPort httpPort) {
    return new DedicatedHttpPortReflectionWrapper(baseUrl, httpPort);
  }
}
