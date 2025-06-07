package tech.intellispaces.ixora.http;

import tech.intellispaces.reflections.framework.annotation.Mapper;
import tech.intellispaces.reflections.framework.annotation.Reflection;

@Reflection(domainClass = HttpStatusDomain.class)
abstract class HttpStatusReflection implements HttpStatus {
  private final int code;

  HttpStatusReflection(int code) {
    this.code = code;
  }

  @Mapper
  @Override
  public int code() {
    return code;
  }

  @Mapper
  @Override
  public boolean isOkStatus() {
    return HttpStatuses.ok().code() == code;
  }
}
