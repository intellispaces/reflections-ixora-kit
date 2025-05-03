package tech.intellispaces.ixora.http;

import java.io.InputStream;

import tech.intellispaces.ixora.data.stream.ByteInputStreams;
import tech.intellispaces.ixora.data.stream.MovableByteInputStream;
import tech.intellispaces.ixora.internet.uri.Uri;
import tech.intellispaces.reflections.annotation.Mapper;
import tech.intellispaces.reflections.annotation.ObjectHandle;

@ObjectHandle(HttpRequestDomain.class)
abstract class HttpRequestImpl implements UnmovableHttpRequest {
  private final HttpMethod method;
  private final Uri requestURI;
  private final MovableByteInputStream bodyStream;

  HttpRequestImpl(HttpMethod method, Uri requestURI) {
    this.method = method;
    this.requestURI = requestURI;
    this.bodyStream = ByteInputStreams.handleOf(InputStream.nullInputStream());
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
