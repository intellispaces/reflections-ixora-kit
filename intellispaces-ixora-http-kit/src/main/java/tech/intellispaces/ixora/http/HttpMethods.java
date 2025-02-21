package tech.intellispaces.ixora.http;

import tech.intellispaces.commons.base.exception.UnexpectedExceptions;

public final class HttpMethods {

  public static HttpMethodHandle get(String name) {
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

  public static HttpMethodHandle get() {
    return GET;
  }

  public static HttpMethodHandle head() {
    return HEAD;
  }

  public static HttpMethodHandle post() {
    return POST;
  }

  public static HttpMethodHandle put() {
    return PUT;
  }

  public static HttpMethodHandle patch() {
    return PATCH;
  }

  public static HttpMethodHandle delete() {
    return DELETE;
  }

  public static HttpMethodHandle options() {
    return OPTIONS;
  }

  public static HttpMethodHandle trace() {
    return TRACE;
  }

  private HttpMethods() {}

  private static final HttpMethodHandle GET = new HttpMethodHandleImplWrapper("GET");
  private static final HttpMethodHandle HEAD = new HttpMethodHandleImplWrapper("HEAD");
  private static final HttpMethodHandle POST = new HttpMethodHandleImplWrapper("POST");
  private static final HttpMethodHandle PUT = new HttpMethodHandleImplWrapper("PUT");
  private static final HttpMethodHandle PATCH = new HttpMethodHandleImplWrapper("PATCH");
  private static final HttpMethodHandle DELETE = new HttpMethodHandleImplWrapper("DELETE");
  private static final HttpMethodHandle OPTIONS = new HttpMethodHandleImplWrapper("OPTIONS");
  private static final HttpMethodHandle TRACE = new HttpMethodHandleImplWrapper("TRACE");
}
