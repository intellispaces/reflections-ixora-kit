package tech.intellispaces.ixora.http;

import tech.intellispaces.ixora.internet.uri.Uri;
import tech.intellispaces.ixora.internet.uri.UrisCustomizer;

public interface HttpRequestsCustomizer {

  static UnmovableHttpRequest get(HttpMethod method, Uri requestURI) {
    return new HttpRequestImplWrapper(method, requestURI);
  }

  static UnmovableHttpRequest get(HttpMethod method, String requestURI) {
    return HttpRequestsCustomizer.get(method, UrisCustomizer.get(requestURI));
  }
}
