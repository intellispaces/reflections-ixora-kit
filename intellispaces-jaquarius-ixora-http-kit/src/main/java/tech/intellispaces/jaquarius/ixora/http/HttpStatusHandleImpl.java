package tech.intellispaces.jaquarius.ixora.http;

import tech.intellispaces.jaquarius.annotation.Mapper;
import tech.intellispaces.jaquarius.annotation.ObjectHandle;

@ObjectHandle(HttpStatusDomain.class)
abstract class HttpStatusHandleImpl implements UnmovableHttpStatusHandle {
  private final int code;

  HttpStatusHandleImpl(int code) {
    this.code = code;
  }

  @Mapper
  @Override
  public Integer code() {
    return code;
  }

  @Mapper
  @Override
  public Boolean isOkStatus() {
    return HttpStatuses.ok().code() == code;
  }
}
