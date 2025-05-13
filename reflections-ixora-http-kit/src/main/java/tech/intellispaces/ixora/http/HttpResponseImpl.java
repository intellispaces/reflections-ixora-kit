package tech.intellispaces.ixora.http;

import tech.intellispaces.commons.collection.ArraysFunctions;
import tech.intellispaces.commons.exception.UnexpectedExceptions;
import tech.intellispaces.commons.text.StringFunctions;
import tech.intellispaces.ixora.data.stream.ByteInputStreams;
import tech.intellispaces.ixora.data.stream.MovableByteInputStreamReflection;
import tech.intellispaces.reflections.framework.annotation.Mapper;
import tech.intellispaces.reflections.framework.annotation.MapperOfMoving;
import tech.intellispaces.reflections.framework.annotation.Reflection;

import java.io.InputStream;

@Reflection(HttpResponseDomain.class)
abstract class HttpResponseImpl implements UnmovableHttpResponseReflection {
  private final HttpStatusReflection status;
  private final MovableByteInputStreamReflection bodyStream;

  HttpResponseImpl(HttpStatusReflection status, InputStream body) {
    this.status = status;
    this.bodyStream = ByteInputStreams.handleOf(body);
  }

  HttpResponseImpl(HttpStatusReflection status, String body) {
    this(status, StringFunctions.stringToInputStream(body));
  }

  HttpResponseImpl(HttpStatusReflection status, byte[] body) {
    this(status, ArraysFunctions.arrayToInputStream(body));
  }

  HttpResponseImpl(HttpStatusReflection status) {
    this(status, InputStream.nullInputStream());
  }

  @Mapper
  @Override
  public HttpStatusReflection status() {
    return this.status;
  }

  @MapperOfMoving
  @Override
  public MovableByteInputStreamReflection bodyStream() {
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
