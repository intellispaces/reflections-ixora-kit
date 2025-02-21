package tech.intellispaces.ixora.data.association.exception;

import tech.intellispaces.commons.base.text.StringFunctions;

/**
 * Provider of the exception {@link InvalidPropertyException}.
 */
public interface InvalidPropertyExceptions {

  static InvalidPropertyException withMessage(String message) {
    return new InvalidPropertyException(message);
  }

  static InvalidPropertyException withMessage(String template, Object... params) {
    return new InvalidPropertyException(StringFunctions.resolveTemplate(template, params));
  }

  static InvalidPropertyException withCauseAndMessage(Throwable cause, String message) {
    return new InvalidPropertyException(message, cause);
  }

  static InvalidPropertyException withCauseAndMessage(
      Throwable cause, String template, Object... params
  ) {
    return new InvalidPropertyException(StringFunctions.resolveTemplate(template, params), cause);
  }
}
