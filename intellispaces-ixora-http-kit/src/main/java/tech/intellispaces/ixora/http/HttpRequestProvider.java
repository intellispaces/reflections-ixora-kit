package tech.intellispaces.ixora.http;

import tech.intellispaces.ixora.internet.uri.Uri;
import tech.intellispaces.jaquarius.annotation.ObjectProvider;

@ObjectProvider
public class HttpRequestProvider implements HttpRequestProviderCustomizer {

  @Override
  public UnmovableHttpRequestHandle create(HttpMethod method, Uri requestURI) {
    return new HttpRequestImplWrapper(method, requestURI);
  }
}
