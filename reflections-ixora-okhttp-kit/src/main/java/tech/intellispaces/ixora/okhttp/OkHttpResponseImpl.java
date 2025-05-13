package tech.intellispaces.ixora.okhttp;

import okhttp3.Response;
import okhttp3.ResponseBody;
import tech.intellispaces.ixora.data.stream.ByteInputStreams;
import tech.intellispaces.ixora.data.stream.MovableByteInputStreamReflection;
import tech.intellispaces.ixora.http.HttpStatusReflection;
import tech.intellispaces.ixora.http.HttpStatuses;
import tech.intellispaces.reflections.framework.annotation.Mapper;
import tech.intellispaces.reflections.framework.annotation.MapperOfMoving;
import tech.intellispaces.reflections.framework.annotation.Reflection;

@Reflection(OkHttpResponseDomain.class)
public abstract class OkHttpResponseImpl implements UnmovableOkHttpResponse, UnmovableOkHttpResponseReflection {
  private final Response underlyingResponse;
  private final MovableByteInputStreamReflection bodyStream;

  OkHttpResponseImpl(Response underlyingResponse) {
    this.underlyingResponse = underlyingResponse;

    ResponseBody body = underlyingResponse.body();
    this.bodyStream = (body != null ? ByteInputStreams.handleOf(body.byteStream()) : ByteInputStreams.empty());
  }

  public Response getUnderlyingResponse() {
    return underlyingResponse;
  }

  @Mapper
  @Override
  public HttpStatusReflection status() {
    return HttpStatuses.get(underlyingResponse.code());
  }

  @MapperOfMoving
  @Override
  public MovableByteInputStreamReflection bodyStream() {
    return bodyStream;
  }

  @Override
  public void unbind() {
    underlyingResponse.close();
  }

  @Override
  public void close() {
    unbind();
  }
}
