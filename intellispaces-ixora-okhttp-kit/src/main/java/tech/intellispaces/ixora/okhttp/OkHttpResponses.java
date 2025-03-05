package tech.intellispaces.ixora.okhttp;

import okhttp3.Response;

public interface OkHttpResponses {

  static UnmovableOkHttpResponse get(Response response) {
    return new OkHttpResponseImplWrapper(response);
  }
}
