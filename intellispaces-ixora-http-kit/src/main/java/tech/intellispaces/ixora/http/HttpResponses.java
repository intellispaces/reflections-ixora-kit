package tech.intellispaces.ixora.http;

import java.io.InputStream;

public interface HttpResponses {

  static HttpResponseHandle get(HttpStatusHandle status) {
    return new HttpResponseImplWrapper(status);
  }

  static HttpResponseHandle get(HttpStatusHandle status, InputStream body) {
    return new HttpResponseImplWrapper(status, body);
  }

  static HttpResponseHandle get(HttpStatusHandle status, String body) {
    return new HttpResponseImplWrapper(status, body);
  }

  static HttpResponseHandle get(HttpStatusHandle status, byte[] body) {
    return new HttpResponseImplWrapper(status, body);
  }

  static HttpResponseHandle ok(String body) {
    return get(HttpStatuses.ok(), body);
  }

  static HttpResponseHandle notFound() {
    return get(HttpStatuses.notFound());
  }

  static HttpResponseHandle notFound(String body) {
    return get(HttpStatuses.notFound(), body);
  }
}
