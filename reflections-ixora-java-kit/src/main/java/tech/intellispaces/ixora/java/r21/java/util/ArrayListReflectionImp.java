package tech.intellispaces.ixora.java.r21.java.util;

import java.util.ArrayList;

import tech.intellispaces.reflections.framework.annotation.Reflection;
import tech.intellispaces.reflections.framework.reflection.NativeReflection;

@Reflection(ArrayListDomain.class)
public abstract class ArrayListReflectionImp implements MovableArrayListReflection, NativeReflection<ArrayListDomain> {
  private final ArrayList<?> arrayList;

  public ArrayListReflectionImp(ArrayList<?> arrayList) {
    this.arrayList = arrayList;
  }

  @Override
  public Object boundObject() {
    return arrayList;
  }
}
