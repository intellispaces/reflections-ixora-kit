package tech.intellispaces.ixora.http;

import java.io.InputStream;

import tech.intellispaces.jaquarius.annotation.Factory;

@Factory
public class HttpResponseFactory implements HttpResponseAssistantCustomizer {

  @Override
  public HttpResponseHandle create(HttpStatusHandle status) {
    return new HttpResponseImplWrapper(status);
  }

  @Override
  public HttpResponseHandle create(HttpStatusHandle status, InputStream body) {
    return new HttpResponseImplWrapper(status, body);
  }

  @Override
  public HttpResponseHandle create(HttpStatusHandle status, String body) {
    return new HttpResponseImplWrapper(status, body);
  }

  @Override
  public HttpResponseHandle create(HttpStatusHandle status, byte[] body) {
    return new HttpResponseImplWrapper(status, body);
  }
}
