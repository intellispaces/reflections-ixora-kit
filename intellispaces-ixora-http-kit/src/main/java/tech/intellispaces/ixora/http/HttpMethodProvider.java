package tech.intellispaces.ixora.http;

import tech.intellispaces.jaquarius.annotation.ObjectProvider;

@ObjectProvider
public class HttpMethodProvider implements HttpMethodProviderCustomizer {

  public HttpMethodHandle get() {
    return GET;
  }

  public HttpMethodHandle head() {
    return HEAD;
  }

  public HttpMethodHandle post() {
    return POST;
  }

  public HttpMethodHandle put() {
    return PUT;
  }

  public HttpMethodHandle patch() {
    return PATCH;
  }

  public HttpMethodHandle delete() {
    return DELETE;
  }

  public HttpMethodHandle options() {
    return OPTIONS;
  }

  public HttpMethodHandle trace() {
    return TRACE;
  }

  private static final HttpMethodHandle GET = new HttpMethodImplWrapper("GET");
  private static final HttpMethodHandle HEAD = new HttpMethodImplWrapper("HEAD");
  private static final HttpMethodHandle POST = new HttpMethodImplWrapper("POST");
  private static final HttpMethodHandle PUT = new HttpMethodImplWrapper("PUT");
  private static final HttpMethodHandle PATCH = new HttpMethodImplWrapper("PATCH");
  private static final HttpMethodHandle DELETE = new HttpMethodImplWrapper("DELETE");
  private static final HttpMethodHandle OPTIONS = new HttpMethodImplWrapper("OPTIONS");
  private static final HttpMethodHandle TRACE = new HttpMethodImplWrapper("TRACE");
}
