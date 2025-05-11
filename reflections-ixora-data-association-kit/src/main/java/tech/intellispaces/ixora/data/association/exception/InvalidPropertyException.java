package tech.intellispaces.ixora.data.association.exception;

import tech.intellispaces.reflections.framework.exception.TraverseException;

public class InvalidPropertyException extends TraverseException {

  public InvalidPropertyException(String message) {
    super(message);
  }

  public InvalidPropertyException(String message, Throwable cause) {
    super(message, cause);
  }
}
