package tech.intellispaces.ixora.http.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Inbound HTTP logical port.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface HttpPort {

  /**
   * HTTP ontology classes.
   */
  Class<?>[] value();
}
