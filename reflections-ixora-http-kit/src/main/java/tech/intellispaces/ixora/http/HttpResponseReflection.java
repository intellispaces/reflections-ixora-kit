package tech.intellispaces.ixora.http;

import java.io.InputStream;

import tech.intellispaces.commons.collection.ArraysFunctions;
import tech.intellispaces.commons.exception.UnexpectedExceptions;
import tech.intellispaces.commons.text.StringFunctions;
import tech.intellispaces.ixora.data.stream.ByteInputStreams;
import tech.intellispaces.ixora.data.stream.MovableByteInputStream;
import tech.intellispaces.reflections.framework.annotation.Mapper;
import tech.intellispaces.reflections.framework.annotation.MapperOfMoving;
import tech.intellispaces.reflections.framework.annotation.Reflection;

@Reflection(domainClass = HttpResponseDomain.class)
abstract class HttpResponseReflection implements HttpResponse {
  private final HttpStatus status;
  private final MovableByteInputStream bodyStream;

  HttpResponseReflection(HttpStatus status, InputStream body) {
    this.status = status;
    this.bodyStream = ByteInputStreams.reflectionOf(body);
  }

  HttpResponseReflection(HttpStatus status, String body) {
    this(status, StringFunctions.stringToInputStream(body));
  }

  HttpResponseReflection(HttpStatus status, byte[] body) {
    this(status, ArraysFunctions.arrayToInputStream(body));
  }

  HttpResponseReflection(HttpStatus status) {
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
