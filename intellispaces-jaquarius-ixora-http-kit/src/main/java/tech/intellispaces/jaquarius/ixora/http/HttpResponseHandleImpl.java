package tech.intellispaces.jaquarius.ixora.http;

import tech.intellispaces.commons.base.collection.ArraysFunctions;
import tech.intellispaces.commons.base.text.StringFunctions;
import tech.intellispaces.jaquarius.annotation.Mapper;
import tech.intellispaces.jaquarius.annotation.MapperOfMoving;
import tech.intellispaces.jaquarius.annotation.ObjectHandle;
import tech.intellispaces.jaquarius.ixora.data.stream.DataStreams;
import tech.intellispaces.jaquarius.ixora.data.stream.MovableByteInputStreamHandle;

import java.io.InputStream;

@ObjectHandle(HttpResponseDomain.class)
abstract class HttpResponseHandleImpl implements UnmovableHttpResponseHandle {
  private final HttpStatusHandleImpl status;
  private final MovableByteInputStreamHandle bodyStream;

  HttpResponseHandleImpl(HttpStatusHandleImpl status, InputStream body) {
    this.status = status;
    this.bodyStream = DataStreams.get(body);
  }

  HttpResponseHandleImpl(HttpStatusHandleImpl status, String body) {
    this(status, StringFunctions.stringToInputStream(body));
  }

  HttpResponseHandleImpl(HttpStatusHandleImpl status, byte[] body) {
    this(status, ArraysFunctions.arrayToInputStream(body));
  }

  HttpResponseHandleImpl(HttpStatusHandleImpl status) {
    this(status, InputStream.nullInputStream());
  }

  @Mapper
  @Override
  public HttpStatusHandle status() {
    return this.status;
  }

  @MapperOfMoving
  @Override
  public MovableByteInputStreamHandle bodyStream() {
    return bodyStream;
  }
}
