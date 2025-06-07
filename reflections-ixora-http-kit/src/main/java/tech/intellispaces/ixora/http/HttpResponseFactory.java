package tech.intellispaces.ixora.http;

import java.io.InputStream;

import tech.intellispaces.reflections.framework.annotation.Factory;

@Factory
public class HttpResponseFactory implements HttpResponseAssistantCustomizer {

  @Override
  public HttpResponse create(HttpStatus status) {
    return new HttpResponseReflectionWrapper(status);
  }

  @Override
  public HttpResponse create(HttpStatus status, InputStream body) {
    return new HttpResponseReflectionWrapper(status, body);
  }

  @Override
  public HttpResponse create(HttpStatus status, String body) {
    return new HttpResponseReflectionWrapper(status, body);
  }

  @Override
  public HttpResponse create(HttpStatus status, byte[] body) {
    return new HttpResponseReflectionWrapper(status, body);
  }
}
