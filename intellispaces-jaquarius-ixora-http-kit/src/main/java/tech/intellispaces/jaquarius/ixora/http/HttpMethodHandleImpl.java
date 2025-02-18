package tech.intellispaces.jaquarius.ixora.http;

import tech.intellispaces.jaquarius.annotation.Mapper;
import tech.intellispaces.jaquarius.annotation.ObjectHandle;

@ObjectHandle(HttpMethodDomain.class)
abstract class HttpMethodHandleImpl implements UnmovableHttpMethodHandle {
  private final String name;

  HttpMethodHandleImpl(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  @Mapper
  @Override
  public String name() {
    return name;
  }

  @Mapper
  @Override
  public Boolean isGetMethod() {
    return HttpMethods.get().name().equals(name);
  }
}
