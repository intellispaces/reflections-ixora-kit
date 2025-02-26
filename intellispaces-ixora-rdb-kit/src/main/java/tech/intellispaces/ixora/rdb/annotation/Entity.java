package tech.intellispaces.ixora.rdb.annotation;

import tech.intellispaces.ixora.rdb.annotation.processor.EntityProcessor;
import tech.intellispaces.jaquarius.annotation.AnnotationProcessor;
import tech.intellispaces.jaquarius.annotation.Dataset;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Dataset
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@AnnotationProcessor(EntityProcessor.class)
public @interface Entity {
}
