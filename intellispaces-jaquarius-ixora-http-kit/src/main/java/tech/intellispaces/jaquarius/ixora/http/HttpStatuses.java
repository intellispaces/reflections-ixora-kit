package tech.intellispaces.jaquarius.ixora.http;

import tech.intellispaces.commons.base.exception.UnexpectedExceptions;

public final class HttpStatuses {

  public static HttpStatusHandleImpl ok() {
    return STATUS_OK;
  }

  public static HttpStatusHandleImpl created() {
    return STATUS_CREATED;
  }

  public static HttpStatusHandleImpl accepted() {
    return STATUS_ACCEPTED;
  }

  public static HttpStatusHandleImpl noContent() {
    return STATUS_NO_CONTENT;
  }

  public static HttpStatusHandleImpl movedPermanently() {
    return STATUS_MOVED_PERMANENTLY;
  }

  public static HttpStatusHandleImpl notModified() {
    return STATUS_NOT_MODIFIED;
  }

  public static HttpStatusHandleImpl badRequest() {
    return STATUS_BAD_REQUEST;
  }

  public static HttpStatusHandleImpl unauthorized() {
    return STATUS_UNAUTHORIZED;
  }

  public static HttpStatusHandleImpl forbidden() {
    return STATUS_FORBIDDEN;
  }

  public static HttpStatusHandleImpl notFound() {
    return STATUS_NOT_FOUND;
  }

  public static HttpStatusHandleImpl notAcceptable() {
    return STATUS_NOT_ACCEPTABLE;
  }

  public static HttpStatusHandleImpl internalServerError() {
    return STATUS_INTERNAL_SERVER_ERROR;
  }

  public static HttpStatusHandleImpl get(int code) {
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

  private static final HttpStatusHandleImpl STATUS_OK = new HttpStatusHandleImplWrapper(CODE_OK);
  private static final HttpStatusHandleImpl STATUS_CREATED = new HttpStatusHandleImplWrapper(CODE_CREATED);
  private static final HttpStatusHandleImpl STATUS_ACCEPTED = new HttpStatusHandleImplWrapper(CODE_ACCEPTED);
  private static final HttpStatusHandleImpl STATUS_NO_CONTENT = new HttpStatusHandleImplWrapper(CODE_NO_CONTENT);
  private static final HttpStatusHandleImpl STATUS_MOVED_PERMANENTLY = new HttpStatusHandleImplWrapper(CODE_MOVED_PERMANENTLY);
  private static final HttpStatusHandleImpl STATUS_NOT_MODIFIED = new HttpStatusHandleImplWrapper(CODE_NOT_MODIFIED);
  private static final HttpStatusHandleImpl STATUS_BAD_REQUEST = new HttpStatusHandleImplWrapper(CODE_BAD_REQUEST);
  private static final HttpStatusHandleImpl STATUS_UNAUTHORIZED = new HttpStatusHandleImplWrapper(CODE_UNAUTHORIZED);
  private static final HttpStatusHandleImpl STATUS_FORBIDDEN = new HttpStatusHandleImplWrapper(CODE_FORBIDDEN);
  private static final HttpStatusHandleImpl STATUS_NOT_FOUND = new HttpStatusHandleImplWrapper(CODE_NOT_FOUND);
  private static final HttpStatusHandleImpl STATUS_NOT_ACCEPTABLE = new HttpStatusHandleImplWrapper(CODE_NOT_ACCEPTABLE);
  private static final HttpStatusHandleImpl STATUS_INTERNAL_SERVER_ERROR = new HttpStatusHandleImplWrapper(CODE_INTERNAL_SERVER_ERROR);
}
