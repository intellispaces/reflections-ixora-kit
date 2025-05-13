package tech.intellispaces.ixora.http;

import tech.intellispaces.reflections.framework.annotation.Factory;

@Factory
public class HttpStatusFactory implements HttpStatusAssistantCustomizer {

  @Override
  public HttpStatusReflection ok() {
    return STATUS_OK;
  }

  @Override
  public HttpStatusReflection created() {
    return STATUS_CREATED;
  }

  @Override
  public HttpStatusReflection accepted() {
    return STATUS_ACCEPTED;
  }

  @Override
  public HttpStatusReflection noContent() {
    return STATUS_NO_CONTENT;
  }

  @Override
  public HttpStatusReflection movedPermanently() {
    return STATUS_MOVED_PERMANENTLY;
  }

  @Override
  public HttpStatusReflection notModified() {
    return STATUS_NOT_MODIFIED;
  }

  @Override
  public HttpStatusReflection badRequest() {
    return STATUS_BAD_REQUEST;
  }

  @Override
  public HttpStatusReflection unauthorized() {
    return STATUS_UNAUTHORIZED;
  }

  @Override
  public HttpStatusReflection forbidden() {
    return STATUS_FORBIDDEN;
  }

  @Override
  public HttpStatusReflection notFound() {
    return STATUS_NOT_FOUND;
  }

  @Override
  public HttpStatusReflection notAcceptable() {
    return STATUS_NOT_ACCEPTABLE;
  }

  @Override
  public HttpStatusReflection internalServerError() {
    return STATUS_INTERNAL_SERVER_ERROR;
  }

  private final HttpStatusReflection STATUS_OK = new HttpStatusImplWrapper(HttpStatusCodes.OK);
  private final HttpStatusReflection STATUS_CREATED = new HttpStatusImplWrapper(HttpStatusCodes.CREATED);
  private final HttpStatusReflection STATUS_ACCEPTED = new HttpStatusImplWrapper(HttpStatusCodes.ACCEPTED);
  private final HttpStatusReflection STATUS_NO_CONTENT = new HttpStatusImplWrapper(HttpStatusCodes.NO_CONTENT);
  private final HttpStatusReflection STATUS_MOVED_PERMANENTLY = new HttpStatusImplWrapper(HttpStatusCodes.MOVED_PERMANENTLY);
  private final HttpStatusReflection STATUS_NOT_MODIFIED = new HttpStatusImplWrapper(HttpStatusCodes.NOT_MODIFIED);
  private final HttpStatusReflection STATUS_BAD_REQUEST = new HttpStatusImplWrapper(HttpStatusCodes.BAD_REQUEST);
  private final HttpStatusReflection STATUS_UNAUTHORIZED = new HttpStatusImplWrapper(HttpStatusCodes.UNAUTHORIZED);
  private final HttpStatusReflection STATUS_FORBIDDEN = new HttpStatusImplWrapper(HttpStatusCodes.FORBIDDEN);
  private final HttpStatusReflection STATUS_NOT_FOUND = new HttpStatusImplWrapper(HttpStatusCodes.NOT_FOUND);
  private final HttpStatusReflection STATUS_NOT_ACCEPTABLE = new HttpStatusImplWrapper(HttpStatusCodes.NOT_ACCEPTABLE);
  private final HttpStatusReflection STATUS_INTERNAL_SERVER_ERROR = new HttpStatusImplWrapper(HttpStatusCodes.INTERNAL_SERVER_ERROR);
}
