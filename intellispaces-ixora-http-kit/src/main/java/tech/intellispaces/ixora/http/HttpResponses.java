package tech.intellispaces.ixora.http;

import java.io.InputStream;

public interface HttpResponses {

  static HttpResponse get(HttpStatusImpl status) {
    return new HttpResponseImplWrapper(status);
  }

  static HttpResponse get(HttpStatusImpl status, InputStream body) {
    return new HttpResponseImplWrapper(status, body);
  }

  static HttpResponse get(HttpStatusImpl status, String body) {
    return new HttpResponseImplWrapper(status, body);
  }

  static HttpResponse get(HttpStatusImpl status, byte[] body) {
    return new HttpResponseImplWrapper(status, body);
  }

  static HttpResponse ok(String body) {
    return get(HttpStatuses.ok(), body);
  }

  static HttpResponse notFound() {
    return get(HttpStatuses.notFound());
  }

  static HttpResponse notFound(String body) {
    return get(HttpStatuses.notFound(), body);
  }
}
