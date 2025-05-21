package tech.intellispaces.ixora.http;

import java.io.InputStream;

import tech.intellispaces.ixora.data.stream.ByteInputStreams;
import tech.intellispaces.ixora.data.stream.MovableByteInputStream;
import tech.intellispaces.ixora.internet.uri.Uri;
import tech.intellispaces.reflections.framework.annotation.Mapper;
import tech.intellispaces.reflections.framework.annotation.Reflection;

@Reflection(HttpRequestDomain.class)
abstract class HttpRequestReflectionImpl implements UnmovableHttpRequest {
  private final HttpMethod method;
  private final Uri requestURI;
  private final MovableByteInputStream bodyStream;

  HttpRequestReflectionImpl(HttpMethod method, Uri requestURI) {
    this.method = method;
    this.requestURI = requestURI;
    this.bodyStream = ByteInputStreams.reflectionOf(InputStream.nullInputStream());
  }

  @Mapper
  @Override
  public Uri requestURI() {
    return this.requestURI;
  }

  @Mapper
  @Override
  public HttpMethod method() {
    return this.method;
  }

  @Override
  public MovableByteInputStream bodyStream() {
    return bodyStream;
  }
}
