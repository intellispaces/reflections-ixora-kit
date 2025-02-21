package tech.intellispaces.ixora.http;

import java.io.InputStream;

public interface HttpResponses {

  static HttpResponseHandle get(HttpStatusHandleImpl status) {
    return new HttpResponseHandleImplWrapper(status);
  }

  static HttpResponseHandle get(HttpStatusHandleImpl status, InputStream body) {
    return new HttpResponseHandleImplWrapper(status, body);
  }

  static HttpResponseHandle get(HttpStatusHandleImpl status, String body) {
    return new HttpResponseHandleImplWrapper(status, body);
  }

  static HttpResponseHandle get(HttpStatusHandleImpl status, byte[] body) {
    return new HttpResponseHandleImplWrapper(status, body);
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
