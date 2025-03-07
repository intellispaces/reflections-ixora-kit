package tech.intellispaces.ixora.http.exception;

import tech.intellispaces.commons.text.StringFunctions;

/**
 * Provider of the exception {@link HttpException}.
 */
public interface HttpExceptions {

  static HttpException withMessage(String message) {
    return new HttpException(message);
  }

  static HttpException withMessage(String template, Object... params) {
    return new HttpException(StringFunctions.resolveTemplate(template, params));
  }

  static HttpException withCauseAndMessage(Throwable cause, String message) {
    return new HttpException(message, cause);
  }

  static HttpException withCauseAndMessage(
      Throwable cause, String template, Object... params
  ) {
    return new HttpException(StringFunctions.resolveTemplate(template, params), cause);
  }
}
