package tech.intellispaces.ixora.java.collection;

import java.util.ArrayList;

import tech.intellispaces.reflections.framework.annotation.Reflection;
import tech.intellispaces.reflections.framework.reflection.NativePoint;

@Reflection(domainClass = ArrayListDomain.class)
public abstract class ArrayListReflectionImp implements MovableArrayList, NativePoint {
  private final ArrayList<?> arrayList;

  public ArrayListReflectionImp(ArrayList<?> arrayList) {
    this.arrayList = arrayList;
  }

  @Override
  public Object boundObject() {
    return arrayList;
  }
}
