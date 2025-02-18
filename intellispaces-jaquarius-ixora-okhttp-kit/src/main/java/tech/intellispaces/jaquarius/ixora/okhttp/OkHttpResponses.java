package tech.intellispaces.jaquarius.ixora.okhttp;

import okhttp3.Response;

public interface OkHttpResponses {

  static UnmovableOkHttpResponseHandle get(Response response) {
    return new OkHttpResponseHandleImplWrapper(response);
  }
}
