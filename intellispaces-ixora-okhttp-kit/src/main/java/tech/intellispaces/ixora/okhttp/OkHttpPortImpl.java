package tech.intellispaces.ixora.okhttp;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import tech.intellispaces.commons.exception.UnexpectedExceptions;
import tech.intellispaces.ixora.http.HttpMethod;
import tech.intellispaces.ixora.http.HttpRequest;
import tech.intellispaces.ixora.http.HttpResponse;
import tech.intellispaces.ixora.http.exception.HttpException;
import tech.intellispaces.ixora.http.exception.HttpExceptions;
import tech.intellispaces.jaquarius.annotation.MapperOfMoving;
import tech.intellispaces.jaquarius.annotation.ObjectHandle;

import java.io.IOException;

@ObjectHandle(OkHttpPortDomain.class)
public abstract class OkHttpPortImpl implements MovableOkHttpPort {
  private final OkHttpClient client;

  public OkHttpPortImpl(OkHttpClient client) {
    this.client = client;
  }

  public OkHttpClient getClient() {
    return client;
  }

  @Override
  @MapperOfMoving
  public HttpResponse exchange(HttpRequest request) throws HttpException {
    Request req = buildRequest(request);
    try {
      Response res = client.newCall(req).execute();
      return OkHttpResponses.handleOf(res).asHttpResponse();
    } catch (IOException e) {
      throw HttpExceptions.withCauseAndMessage(e, "Could not call HTTP server");
    }
  }

  private Request buildRequest(HttpRequest request) {
    Request.Builder reqBuilder = new Request.Builder()
        .url(request.requestURI().toString());
    HttpMethod method = request.method();
    if (method.isGetMethod()) {
      reqBuilder = reqBuilder.get();
    } else {
      throw UnexpectedExceptions.withMessage("Unsupported HTTP method: {}", method.name());
    }
    return reqBuilder.build();
  }
}
