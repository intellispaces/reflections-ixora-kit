package tech.intellispaces.ixora.http;

import tech.intellispaces.commons.base.exception.UnexpectedExceptions;

public final class HttpMethods {

  public static HttpMethod get(String name) {
    if (GET.name().equals(name)) {
      return GET;
    } else if (HEAD.name().equals(name)) {
      return HEAD;
    } else if (POST.name().equals(name)) {
      return POST;
    } else if (PUT.name().equals(name)) {
      return PUT;
    } else if (PATCH.name().equals(name)) {
      return PATCH;
    } else if (DELETE.name().equals(name)) {
      return DELETE;
    } else if (OPTIONS.name().equals(name)) {
      return OPTIONS;
    } else if (TRACE.name().equals(name)) {
      return TRACE;
    } else {
      throw UnexpectedExceptions.withMessage("Unsupported HTTP method name {0}", name);
    }
  }

  public static HttpMethod get() {
    return GET;
  }

  public static HttpMethod head() {
    return HEAD;
  }

  public static HttpMethod post() {
    return POST;
  }

  public static HttpMethod put() {
    return PUT;
  }

  public static HttpMethod patch() {
    return PATCH;
  }

  public static HttpMethod delete() {
    return DELETE;
  }

  public static HttpMethod options() {
    return OPTIONS;
  }

  public static HttpMethod trace() {
    return TRACE;
  }

  private HttpMethods() {}

  private static final HttpMethod GET = new HttpMethodImplWrapper("GET");
  private static final HttpMethod HEAD = new HttpMethodImplWrapper("HEAD");
  private static final HttpMethod POST = new HttpMethodImplWrapper("POST");
  private static final HttpMethod PUT = new HttpMethodImplWrapper("PUT");
  private static final HttpMethod PATCH = new HttpMethodImplWrapper("PATCH");
  private static final HttpMethod DELETE = new HttpMethodImplWrapper("DELETE");
  private static final HttpMethod OPTIONS = new HttpMethodImplWrapper("OPTIONS");
  private static final HttpMethod TRACE = new HttpMethodImplWrapper("TRACE");
}
