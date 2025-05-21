package tech.intellispaces.ixora.http;

import java.io.InputStream;

import tech.intellispaces.reflections.framework.annotation.Factory;

@Factory
public class HttpResponseFactory implements HttpResponseAssistantCustomizer {

  @Override
  public HttpResponseReflection create(HttpStatusReflection status) {
    return new HttpResponseReflectionImplWrapper(status);
  }

  @Override
  public HttpResponseReflection create(HttpStatusReflection status, InputStream body) {
    return new HttpResponseReflectionImplWrapper(status, body);
  }

  @Override
  public HttpResponseReflection create(HttpStatusReflection status, String body) {
    return new HttpResponseReflectionImplWrapper(status, body);
  }

  @Override
  public HttpResponseReflection create(HttpStatusReflection status, byte[] body) {
    return new HttpResponseReflectionImplWrapper(status, body);
  }
}
