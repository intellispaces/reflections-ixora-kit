package tech.intellispaces.ixora.http;

import tech.intellispaces.ixora.internet.uri.Uri;
import tech.intellispaces.ixora.internet.uri.Uris;

public interface HttpRequests {

  static UnmovableHttpRequest get(HttpMethod method, Uri requestURI) {
    return new HttpRequestImplWrapper(method, requestURI);
  }

  static UnmovableHttpRequest get(HttpMethod method, String requestURI) {
    return HttpRequests.get(method, Uris.get(requestURI));
  }
}
