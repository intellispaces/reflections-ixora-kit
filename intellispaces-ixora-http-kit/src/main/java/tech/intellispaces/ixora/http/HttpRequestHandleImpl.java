package tech.intellispaces.ixora.http;

import tech.intellispaces.jaquarius.annotation.Mapper;
import tech.intellispaces.jaquarius.annotation.ObjectHandle;
import tech.intellispaces.ixora.data.stream.DataStreams;
import tech.intellispaces.ixora.data.stream.MovableByteInputStreamHandle;
import tech.intellispaces.ixora.internet.uri.UriHandle;

import java.io.InputStream;

@ObjectHandle(HttpRequestDomain.class)
abstract class HttpRequestHandleImpl implements UnmovableHttpRequestHandle {
  private final HttpMethodHandle method;
  private final UriHandle requestURI;
  private final MovableByteInputStreamHandle bodyStream;

  HttpRequestHandleImpl(HttpMethodHandle method, UriHandle requestURI) {
    this.method = method;
    this.requestURI = requestURI;
    this.bodyStream = DataStreams.get(InputStream.nullInputStream());
  }

  @Mapper
  @Override
  public UriHandle requestURI() {
    return this.requestURI;
  }

  @Mapper
  @Override
  public HttpMethodHandle method() {
    return this.method;
  }

  @Override
  public MovableByteInputStreamHandle bodyStream() {
    return bodyStream;
  }
}
