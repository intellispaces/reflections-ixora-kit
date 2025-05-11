package tech.intellispaces.ixora.http.exception;

import tech.intellispaces.reflections.framework.exception.TraverseException;

public class HttpException extends TraverseException {

  public HttpException(String message) {
    super(message);
  }

  public HttpException(String message, Throwable cause) {
    super(message, cause);
  }
}
