package tech.intellispaces.ixora.http;

import tech.intellispaces.reflections.framework.annotation.Factory;

@Factory
public class HttpMethodFactory implements HttpMethodAssistantCustomizer {

  public HttpMethodReflection get() {
    return GET;
  }

  public HttpMethodReflection head() {
    return HEAD;
  }

  public HttpMethodReflection post() {
    return POST;
  }

  public HttpMethodReflection put() {
    return PUT;
  }

  public HttpMethodReflection patch() {
    return PATCH;
  }

  public HttpMethodReflection delete() {
    return DELETE;
  }

  public HttpMethodReflection options() {
    return OPTIONS;
  }

  public HttpMethodReflection trace() {
    return TRACE;
  }

  private static final HttpMethodReflection GET = new HttpMethodReflectionImplWrapper("GET");
  private static final HttpMethodReflection HEAD = new HttpMethodReflectionImplWrapper("HEAD");
  private static final HttpMethodReflection POST = new HttpMethodReflectionImplWrapper("POST");
  private static final HttpMethodReflection PUT = new HttpMethodReflectionImplWrapper("PUT");
  private static final HttpMethodReflection PATCH = new HttpMethodReflectionImplWrapper("PATCH");
  private static final HttpMethodReflection DELETE = new HttpMethodReflectionImplWrapper("DELETE");
  private static final HttpMethodReflection OPTIONS = new HttpMethodReflectionImplWrapper("OPTIONS");
  private static final HttpMethodReflection TRACE = new HttpMethodReflectionImplWrapper("TRACE");
}
