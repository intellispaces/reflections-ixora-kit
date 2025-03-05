package tech.intellispaces.ixora.http;

import tech.intellispaces.jaquarius.annotation.Mapper;
import tech.intellispaces.jaquarius.annotation.ObjectHandle;

@ObjectHandle(HttpStatusDomain.class)
abstract class HttpStatusImpl implements UnmovableHttpStatus {
  private final int code;

  HttpStatusImpl(int code) {
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
