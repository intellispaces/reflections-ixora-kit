package tech.intellispaces.ixora.http;

import tech.intellispaces.jaquarius.annotation.ObjectProvider;

@ObjectProvider
public class HttpStatusProvider implements HttpStatusProviderCustomizer {

  @Override
  public HttpStatusHandle ok() {
    return STATUS_OK;
  }

  @Override
  public HttpStatusHandle created() {
    return STATUS_CREATED;
  }

  @Override
  public HttpStatusHandle accepted() {
    return STATUS_ACCEPTED;
  }

  @Override
  public HttpStatusHandle noContent() {
    return STATUS_NO_CONTENT;
  }

  @Override
  public HttpStatusHandle movedPermanently() {
    return STATUS_MOVED_PERMANENTLY;
  }

  @Override
  public HttpStatusHandle notModified() {
    return STATUS_NOT_MODIFIED;
  }

  @Override
  public HttpStatusHandle badRequest() {
    return STATUS_BAD_REQUEST;
  }

  @Override
  public HttpStatusHandle unauthorized() {
    return STATUS_UNAUTHORIZED;
  }

  @Override
  public HttpStatusHandle forbidden() {
    return STATUS_FORBIDDEN;
  }

  @Override
  public HttpStatusHandle notFound() {
    return STATUS_NOT_FOUND;
  }

  @Override
  public HttpStatusHandle notAcceptable() {
    return STATUS_NOT_ACCEPTABLE;
  }

  @Override
  public HttpStatusHandle internalServerError() {
    return STATUS_INTERNAL_SERVER_ERROR;
  }

  private final HttpStatusHandle STATUS_OK = new HttpStatusImplWrapper(HttpStatusCodes.OK);
  private final HttpStatusHandle STATUS_CREATED = new HttpStatusImplWrapper(HttpStatusCodes.CREATED);
  private final HttpStatusHandle STATUS_ACCEPTED = new HttpStatusImplWrapper(HttpStatusCodes.ACCEPTED);
  private final HttpStatusHandle STATUS_NO_CONTENT = new HttpStatusImplWrapper(HttpStatusCodes.NO_CONTENT);
  private final HttpStatusHandle STATUS_MOVED_PERMANENTLY = new HttpStatusImplWrapper(HttpStatusCodes.MOVED_PERMANENTLY);
  private final HttpStatusHandle STATUS_NOT_MODIFIED = new HttpStatusImplWrapper(HttpStatusCodes.NOT_MODIFIED);
  private final HttpStatusHandle STATUS_BAD_REQUEST = new HttpStatusImplWrapper(HttpStatusCodes.BAD_REQUEST);
  private final HttpStatusHandle STATUS_UNAUTHORIZED = new HttpStatusImplWrapper(HttpStatusCodes.UNAUTHORIZED);
  private final HttpStatusHandle STATUS_FORBIDDEN = new HttpStatusImplWrapper(HttpStatusCodes.FORBIDDEN);
  private final HttpStatusHandle STATUS_NOT_FOUND = new HttpStatusImplWrapper(HttpStatusCodes.NOT_FOUND);
  private final HttpStatusHandle STATUS_NOT_ACCEPTABLE = new HttpStatusImplWrapper(HttpStatusCodes.NOT_ACCEPTABLE);
  private final HttpStatusHandle STATUS_INTERNAL_SERVER_ERROR = new HttpStatusImplWrapper(HttpStatusCodes.INTERNAL_SERVER_ERROR);
}
