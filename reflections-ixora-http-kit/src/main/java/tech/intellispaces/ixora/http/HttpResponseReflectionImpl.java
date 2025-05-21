package tech.intellispaces.ixora.http;

import java.io.InputStream;

import tech.intellispaces.commons.collection.ArraysFunctions;
import tech.intellispaces.commons.exception.UnexpectedExceptions;
import tech.intellispaces.commons.text.StringFunctions;
import tech.intellispaces.ixora.data.stream.ByteInputStreams;
import tech.intellispaces.ixora.data.stream.MovableByteInputStreamReflection;
import tech.intellispaces.reflections.framework.annotation.Mapper;
import tech.intellispaces.reflections.framework.annotation.MapperOfMoving;
import tech.intellispaces.reflections.framework.annotation.Reflection;

@Reflection(HttpResponseDomain.class)
abstract class HttpResponseReflectionImpl implements UnmovableHttpResponseReflection {
  private final HttpStatusReflection status;
  private final MovableByteInputStreamReflection bodyStream;

  HttpResponseReflectionImpl(HttpStatusReflection status, InputStream body) {
    this.status = status;
    this.bodyStream = ByteInputStreams.reflectionOf(body);
  }

  HttpResponseReflectionImpl(HttpStatusReflection status, String body) {
    this(status, StringFunctions.stringToInputStream(body));
  }

  HttpResponseReflectionImpl(HttpStatusReflection status, byte[] body) {
    this(status, ArraysFunctions.arrayToInputStream(body));
  }

  HttpResponseReflectionImpl(HttpStatusReflection status) {
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
