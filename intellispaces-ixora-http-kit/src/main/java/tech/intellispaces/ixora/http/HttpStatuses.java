package tech.intellispaces.ixora.http;

import tech.intellispaces.commons.base.exception.UnexpectedExceptions;

public final class HttpStatuses {

  public static HttpStatusImpl ok() {
    return STATUS_OK;
  }

  public static HttpStatusImpl created() {
    return STATUS_CREATED;
  }

  public static HttpStatusImpl accepted() {
    return STATUS_ACCEPTED;
  }

  public static HttpStatusImpl noContent() {
    return STATUS_NO_CONTENT;
  }

  public static HttpStatusImpl movedPermanently() {
    return STATUS_MOVED_PERMANENTLY;
  }

  public static HttpStatusImpl notModified() {
    return STATUS_NOT_MODIFIED;
  }

  public static HttpStatusImpl badRequest() {
    return STATUS_BAD_REQUEST;
  }

  public static HttpStatusImpl unauthorized() {
    return STATUS_UNAUTHORIZED;
  }

  public static HttpStatusImpl forbidden() {
    return STATUS_FORBIDDEN;
  }

  public static HttpStatusImpl notFound() {
    return STATUS_NOT_FOUND;
  }

  public static HttpStatusImpl notAcceptable() {
    return STATUS_NOT_ACCEPTABLE;
  }

  public static HttpStatusImpl internalServerError() {
    return STATUS_INTERNAL_SERVER_ERROR;
  }

  public static HttpStatusImpl get(int code) {
    return switch (code) {
      case CODE_OK -> STATUS_OK;
      case CODE_CREATED -> STATUS_CREATED;
      case CODE_ACCEPTED -> STATUS_ACCEPTED;
      case CODE_NO_CONTENT -> STATUS_NO_CONTENT;
      case CODE_MOVED_PERMANENTLY -> STATUS_MOVED_PERMANENTLY;
      case CODE_NOT_MODIFIED -> STATUS_NOT_MODIFIED;
      case CODE_BAD_REQUEST -> STATUS_BAD_REQUEST;
      case CODE_UNAUTHORIZED -> STATUS_UNAUTHORIZED;
      case CODE_FORBIDDEN -> STATUS_FORBIDDEN;
      case CODE_NOT_FOUND -> STATUS_NOT_FOUND;
      case CODE_NOT_ACCEPTABLE -> STATUS_NOT_ACCEPTABLE;
      case CODE_INTERNAL_SERVER_ERROR -> STATUS_INTERNAL_SERVER_ERROR;
      default -> {
        throw UnexpectedExceptions.withMessage("Unsupported HTTP status code: {}", code);
      }
    };
  }

  private HttpStatuses() {}

  private static final int CODE_OK = 200;
  private static final int CODE_CREATED = 201;
  private static final int CODE_ACCEPTED = 202;
  private static final int CODE_NO_CONTENT = 204;
  private static final int CODE_MOVED_PERMANENTLY = 301;
  private static final int CODE_NOT_MODIFIED = 304;
  private static final int CODE_BAD_REQUEST = 400;
  private static final int CODE_UNAUTHORIZED = 401;
  private static final int CODE_FORBIDDEN = 403;
  private static final int CODE_NOT_FOUND = 404;
  private static final int CODE_NOT_ACCEPTABLE = 406;
  private static final int CODE_INTERNAL_SERVER_ERROR = 500;

  private static final HttpStatusImpl STATUS_OK = new HttpStatusImplWrapper(CODE_OK);
  private static final HttpStatusImpl STATUS_CREATED = new HttpStatusImplWrapper(CODE_CREATED);
  private static final HttpStatusImpl STATUS_ACCEPTED = new HttpStatusImplWrapper(CODE_ACCEPTED);
  private static final HttpStatusImpl STATUS_NO_CONTENT = new HttpStatusImplWrapper(CODE_NO_CONTENT);
  private static final HttpStatusImpl STATUS_MOVED_PERMANENTLY = new HttpStatusImplWrapper(CODE_MOVED_PERMANENTLY);
  private static final HttpStatusImpl STATUS_NOT_MODIFIED = new HttpStatusImplWrapper(CODE_NOT_MODIFIED);
  private static final HttpStatusImpl STATUS_BAD_REQUEST = new HttpStatusImplWrapper(CODE_BAD_REQUEST);
  private static final HttpStatusImpl STATUS_UNAUTHORIZED = new HttpStatusImplWrapper(CODE_UNAUTHORIZED);
  private static final HttpStatusImpl STATUS_FORBIDDEN = new HttpStatusImplWrapper(CODE_FORBIDDEN);
  private static final HttpStatusImpl STATUS_NOT_FOUND = new HttpStatusImplWrapper(CODE_NOT_FOUND);
  private static final HttpStatusImpl STATUS_NOT_ACCEPTABLE = new HttpStatusImplWrapper(CODE_NOT_ACCEPTABLE);
  private static final HttpStatusImpl STATUS_INTERNAL_SERVER_ERROR = new HttpStatusImplWrapper(CODE_INTERNAL_SERVER_ERROR);
}
