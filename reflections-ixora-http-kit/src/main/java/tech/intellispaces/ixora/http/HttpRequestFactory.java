package tech.intellispaces.ixora.http;

import tech.intellispaces.ixora.internet.uri.Uri;
import tech.intellispaces.reflections.framework.annotation.Factory;

@Factory
public class HttpRequestFactory implements HttpRequestAssistantCustomizer {

  @Override
  public HttpRequest create(HttpMethod method, Uri requestURI) {
    return new HttpRequestReflectionWrapper(method, requestURI);
  }
}
