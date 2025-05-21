package tech.intellispaces.ixora.rdb.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import tech.intellispaces.ixora.rdb.transaction.TransactionalInterceptor;
import tech.intellispaces.reflections.framework.annotation.ApplyAdvice;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@ApplyAdvice(adviceClass = TransactionalInterceptor.class)
public @interface Transactional {
}
