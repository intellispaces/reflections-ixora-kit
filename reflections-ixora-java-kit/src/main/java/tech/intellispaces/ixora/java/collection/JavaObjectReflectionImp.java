package tech.intellispaces.ixora.java.collection;

import tech.intellispaces.ixora.java.MovableJavaObject;
import tech.intellispaces.reflections.framework.annotation.Reflection;
import tech.intellispaces.reflections.framework.reflection.NativePoint;

@Reflection(domainClass = ArrayListDomain.class)
public abstract class JavaObjectReflectionImp implements MovableJavaObject, NativePoint {
  private final Object object;

  public JavaObjectReflectionImp(Object object) {
    this.object = object;
  }

  @Override
  public Object boundObject() {
    return object;
  }
}
