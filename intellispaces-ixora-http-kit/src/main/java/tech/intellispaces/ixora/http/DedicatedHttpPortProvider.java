package tech.intellispaces.ixora.http;

import tech.intellispaces.jaquarius.annotation.ObjectProvider;

@ObjectProvider
public class DedicatedHttpPortProvider implements DedicatedHttpPortProviderCustomizer {

  @Override
  public MovableDedicatedHttpPortHandle create(String baseUrl, MovableHttpPort httpPort) {
    return new DedicatedHttpPortImplWrapper(baseUrl, httpPort);
  }
}
