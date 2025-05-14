package tech.intellispaces.ixora.http;

import tech.intellispaces.reflections.framework.annotation.Mapper;
import tech.intellispaces.reflections.framework.annotation.Reflection;

@Reflection(HttpStatusDomain.class)
abstract class HttpStatusReflectionImpl implements UnmovableHttpStatus {
  private final int code;

  HttpStatusReflectionImpl(int code) {
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
