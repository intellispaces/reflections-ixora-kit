package tech.intellispaces.ixora.http;

import tech.intellispaces.reflections.framework.annotation.Factory;

@Factory
public class HttpStatusFactory implements HttpStatusAssistantCustomizer {

  @Override
  public HttpStatus ok() {
    return STATUS_OK;
  }

  @Override
  public HttpStatus created() {
    return STATUS_CREATED;
  }

  @Override
  public HttpStatus accepted() {
    return STATUS_ACCEPTED;
  }

  @Override
  public HttpStatus noContent() {
    return STATUS_NO_CONTENT;
  }

  @Override
  public HttpStatus movedPermanently() {
    return STATUS_MOVED_PERMANENTLY;
  }

  @Override
  public HttpStatus notModified() {
    return STATUS_NOT_MODIFIED;
  }

  @Override
  public HttpStatus badRequest() {
    return STATUS_BAD_REQUEST;
  }

  @Override
  public HttpStatus unauthorized() {
    return STATUS_UNAUTHORIZED;
  }

  @Override
  public HttpStatus forbidden() {
    return STATUS_FORBIDDEN;
  }

  @Override
  public HttpStatus notFound() {
    return STATUS_NOT_FOUND;
  }

  @Override
  public HttpStatus notAcceptable() {
    return STATUS_NOT_ACCEPTABLE;
  }

  @Override
  public HttpStatus internalServerError() {
    return STATUS_INTERNAL_SERVER_ERROR;
  }

  private final HttpStatus STATUS_OK = new HttpStatusReflectionWrapper(HttpStatusCodes.OK);
  private final HttpStatus STATUS_CREATED = new HttpStatusReflectionWrapper(HttpStatusCodes.CREATED);
  private final HttpStatus STATUS_ACCEPTED = new HttpStatusReflectionWrapper(HttpStatusCodes.ACCEPTED);
  private final HttpStatus STATUS_NO_CONTENT = new HttpStatusReflectionWrapper(HttpStatusCodes.NO_CONTENT);
  private final HttpStatus STATUS_MOVED_PERMANENTLY = new HttpStatusReflectionWrapper(HttpStatusCodes.MOVED_PERMANENTLY);
  private final HttpStatus STATUS_NOT_MODIFIED = new HttpStatusReflectionWrapper(HttpStatusCodes.NOT_MODIFIED);
  private final HttpStatus STATUS_BAD_REQUEST = new HttpStatusReflectionWrapper(HttpStatusCodes.BAD_REQUEST);
  private final HttpStatus STATUS_UNAUTHORIZED = new HttpStatusReflectionWrapper(HttpStatusCodes.UNAUTHORIZED);
  private final HttpStatus STATUS_FORBIDDEN = new HttpStatusReflectionWrapper(HttpStatusCodes.FORBIDDEN);
  private final HttpStatus STATUS_NOT_FOUND = new HttpStatusReflectionWrapper(HttpStatusCodes.NOT_FOUND);
  private final HttpStatus STATUS_NOT_ACCEPTABLE = new HttpStatusReflectionWrapper(HttpStatusCodes.NOT_ACCEPTABLE);
  private final HttpStatus STATUS_INTERNAL_SERVER_ERROR = new HttpStatusReflectionWrapper(HttpStatusCodes.INTERNAL_SERVER_ERROR);
}
