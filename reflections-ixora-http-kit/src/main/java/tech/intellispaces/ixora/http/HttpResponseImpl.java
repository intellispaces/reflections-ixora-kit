package tech.intellispaces.ixora.http;

import tech.intellispaces.commons.collection.ArraysFunctions;
import tech.intellispaces.commons.exception.UnexpectedExceptions;
import tech.intellispaces.commons.text.StringFunctions;
import tech.intellispaces.ixora.data.stream.ByteInputStreams;
import tech.intellispaces.ixora.data.stream.MovableByteInputStreamHandle;
import tech.intellispaces.reflections.framework.annotation.Mapper;
import tech.intellispaces.reflections.framework.annotation.MapperOfMoving;
import tech.intellispaces.reflections.framework.annotation.ObjectHandle;

import java.io.InputStream;

@ObjectHandle(HttpResponseDomain.class)
abstract class HttpResponseImpl implements UnmovableHttpResponseHandle {
  private final HttpStatusHandle status;
  private final MovableByteInputStreamHandle bodyStream;

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
  public HttpStatusHandle status() {
    return this.status;
  }

  @MapperOfMoving
  @Override
  public MovableByteInputStreamHandle bodyStream() {
    return bodyStream;
  }

  @Override
  public void unbind() {
    try {
      bodyStream.close();
    } catch (Exception e) {
      throw UnexpectedExceptions.withCauseAndMessage(e, "Could not close body stream");
    }
  }

  @Override
  public void close() {
    unbind();
  }
}
