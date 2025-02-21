package tech.intellispaces.ixora.okhttp;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import tech.intellispaces.commons.base.exception.UnexpectedExceptions;
import tech.intellispaces.jaquarius.annotation.MapperOfMoving;
import tech.intellispaces.jaquarius.annotation.ObjectHandle;
import tech.intellispaces.ixora.http.HttpMethodHandle;
import tech.intellispaces.ixora.http.HttpRequestHandle;
import tech.intellispaces.ixora.http.HttpResponseHandle;
import tech.intellispaces.ixora.http.exception.HttpException;
import tech.intellispaces.ixora.http.exception.HttpExceptions;

import java.io.IOException;

@ObjectHandle(OkHttpPortDomain.class)
public abstract class OkHttpPortHandleImpl implements MovableOkHttpPortHandle {
  private final OkHttpClient client;

  public OkHttpPortHandleImpl(OkHttpClient client) {
    this.client = client;
  }

  public OkHttpClient getClient() {
    return client;
  }

  @Override
  @MapperOfMoving
  public HttpResponseHandle exchange(HttpRequestHandle request) throws HttpException {
    Request req = buildRequest(request);
    try {
      Response res = client.newCall(req).execute();
      return OkHttpResponses.get(res).asHttpResponse();
    } catch (IOException e) {
      throw HttpExceptions.withCauseAndMessage(e, "Could not call HTTP server");
    }
  }

  private Request buildRequest(HttpRequestHandle request) {
    Request.Builder reqBuilder = new Request.Builder()
        .url(request.requestURI().toString());
    HttpMethodHandle method = request.method();
    if (method.isGetMethod()) {
      reqBuilder = reqBuilder.get();
    } else {
      throw UnexpectedExceptions.withMessage("Unsupported HTTP method: {}", method.name());
    }
    return reqBuilder.build();
  }
}
