package tech.intellispaces.ixora.http;

import tech.intellispaces.reflections.framework.annotation.Mapper;
import tech.intellispaces.reflections.framework.annotation.Reflection;

@Reflection(HttpMethodDomain.class)
abstract class HttpMethodImpl implements UnmovableHttpMethod {
  private final String name;

  HttpMethodImpl(String name) {
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
  public boolean isGetMethod() {
    return HttpMethods.get().name().equals(name);
  }
}
