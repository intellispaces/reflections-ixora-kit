package tech.intellispaces.ixora.rdb.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import tech.intellispaces.reflections.annotation.Dataset;

/**
 * The persisted entity stored in the relational database.
 */
@Dataset
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface PersistedEntity {
}
