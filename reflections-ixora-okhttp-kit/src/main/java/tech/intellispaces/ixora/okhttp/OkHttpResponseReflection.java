package tech.intellispaces.ixora.okhttp;

import okhttp3.Response;
import okhttp3.ResponseBody;

import tech.intellispaces.ixora.data.stream.ByteInputStreams;
import tech.intellispaces.ixora.data.stream.MovableByteInputStream;
import tech.intellispaces.ixora.http.HttpStatus;
import tech.intellispaces.ixora.http.HttpStatuses;
import tech.intellispaces.reflections.framework.annotation.Mapper;
import tech.intellispaces.reflections.framework.annotation.MapperOfMoving;
import tech.intellispaces.reflections.framework.annotation.Reflection;
import tech.intellispaces.reflections.framework.reflection.NativeReflectionPoint;

@Reflection(domainClass = OkHttpResponseDomain.class)
public abstract class OkHttpResponseReflection implements OkHttpResponse, NativeReflectionPoint {
  private final Response underlyingResponse;
  private final MovableByteInputStream bodyStream;

  OkHttpResponseReflection(Response underlyingResponse) {
    this.underlyingResponse = underlyingResponse;

    ResponseBody body = underlyingResponse.body();
    this.bodyStream = (body != null ? ByteInputStreams.reflectionOf(body.byteStream()) : ByteInputStreams.empty());
  }

  @Override
  public Response boundObject() {
    return underlyingResponse;
  }

  @Mapper
  @Override
  public HttpStatus status() {
    return HttpStatuses.get(underlyingResponse.code());
  }

  @MapperOfMoving
  @Override
  public MovableByteInputStream bodyStream() {
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
