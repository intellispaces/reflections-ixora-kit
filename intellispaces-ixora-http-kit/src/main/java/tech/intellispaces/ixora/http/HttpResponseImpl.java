package tech.intellispaces.ixora.http;

import tech.intellispaces.commons.collection.ArraysFunctions;
import tech.intellispaces.commons.text.StringFunctions;
import tech.intellispaces.ixora.data.stream.ByteInputStreams;
import tech.intellispaces.ixora.data.stream.MovableByteInputStream;
import tech.intellispaces.jaquarius.annotation.Mapper;
import tech.intellispaces.jaquarius.annotation.MapperOfMoving;
import tech.intellispaces.jaquarius.annotation.ObjectHandle;

import java.io.InputStream;

@ObjectHandle(HttpResponseDomain.class)
abstract class HttpResponseImpl implements UnmovableHttpResponse {
  private final HttpStatusHandle status;
  private final MovableByteInputStream bodyStream;

  HttpResponseImpl(HttpStatusHandle status, InputStream body) {
    this.status = status;
    this.bodyStream = ByteInputStreams.handleOf(body);
  }

  HttpResponseImpl(HttpStatusHandle status, String body) {
    this(status, StringFunctions.stringToInputStream(body));
  }

  HttpResponseImpl(HttpStatusHandle status, byte[] body) {
    this(status, ArraysFunctions.arrayToInputStream(body));
  }

  HttpResponseImpl(HttpStatusHandle status) {
    this(status, InputStream.nullInputStream());
  }

  @Mapper
  @Override
  public HttpStatus status() {
    return this.status;
  }

  @MapperOfMoving
  @Override
  public MovableByteInputStream bodyStream() {
    return bodyStream;
  }
}
