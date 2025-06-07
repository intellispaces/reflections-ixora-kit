package tech.intellispaces.ixora.http;

import tech.intellispaces.reflections.framework.annotation.Factory;

@Factory
public class HttpMethodFactory implements HttpMethodAssistantCustomizer {

  public HttpMethod get() {
    return GET;
  }

  public HttpMethod head() {
    return HEAD;
  }

  public HttpMethod post() {
    return POST;
  }

  public HttpMethod put() {
    return PUT;
  }

  public HttpMethod patch() {
    return PATCH;
  }

  public HttpMethod delete() {
    return DELETE;
  }

  public HttpMethod options() {
    return OPTIONS;
  }

  public HttpMethod trace() {
    return TRACE;
  }

  private static final HttpMethod GET = new HttpMethodReflectionWrapper("GET");
  private static final HttpMethod HEAD = new HttpMethodReflectionWrapper("HEAD");
  private static final HttpMethod POST = new HttpMethodReflectionWrapper("POST");
  private static final HttpMethod PUT = new HttpMethodReflectionWrapper("PUT");
  private static final HttpMethod PATCH = new HttpMethodReflectionWrapper("PATCH");
  private static final HttpMethod DELETE = new HttpMethodReflectionWrapper("DELETE");
  private static final HttpMethod OPTIONS = new HttpMethodReflectionWrapper("OPTIONS");
  private static final HttpMethod TRACE = new HttpMethodReflectionWrapper("TRACE");
}
