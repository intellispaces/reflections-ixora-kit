package tech.intellispaces.ixora.http;

import tech.intellispaces.jaquarius.annotation.ObjectFactory;

@ObjectFactory
public class DedicatedHttpPortFactory implements DedicatedHttpPortAssistantExtension {

  @Override
  public MovableDedicatedHttpPortHandle create(String baseUrl, MovableHttpPort httpPort) {
    return new DedicatedHttpPortImplWrapper(baseUrl, httpPort);
  }
}
