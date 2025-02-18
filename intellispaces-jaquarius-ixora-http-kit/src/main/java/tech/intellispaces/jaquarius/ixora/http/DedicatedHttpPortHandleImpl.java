package tech.intellispaces.jaquarius.ixora.http;

import tech.intellispaces.jaquarius.annotation.AutoGuide;
import tech.intellispaces.jaquarius.annotation.Mapper;
import tech.intellispaces.jaquarius.annotation.MapperOfMoving;
import tech.intellispaces.jaquarius.annotation.ObjectHandle;
import tech.intellispaces.jaquarius.ixora.data.stream.ByteStreams;
import tech.intellispaces.jaquarius.ixora.data.stream.InputDataStreamHandle;
import tech.intellispaces.jaquarius.ixora.http.exception.HttpException;
import tech.intellispaces.jaquarius.ixora.internet.uri.JoinBasePathStringWithEndpointStringGuide;
import tech.intellispaces.jaquarius.ixora.internet.uri.UriHandle;
import tech.intellispaces.jaquarius.ixora.internet.uri.Uris;

@ObjectHandle(DedicatedHttpPortDomain.class)
public abstract class DedicatedHttpPortHandleImpl implements MovableDedicatedHttpPortHandle {
  private final String baseUrl;
  private final MovableHttpPortHandle underlyingPort;

  public DedicatedHttpPortHandleImpl(String baseUrl, MovableHttpPortHandle underlyingPort) {
    this.baseUrl = baseUrl;
    this.underlyingPort = underlyingPort;
  }

  public String getBaseUrl() {
    return baseUrl;
  }

  public MovableHttpPortHandle getUnderlyingPort() {
    return underlyingPort;
  }

  @AutoGuide
  abstract JoinBasePathStringWithEndpointStringGuide joinUrlGuide();

  @Override
  @MapperOfMoving
  public HttpResponseHandle exchange(
      String endpoint, HttpMethodHandle method
  ) throws HttpException {
    return exchange(
        endpoint,
        method,
        null,
        ByteStreams.empty()
    );
  }

  @Override
  @MapperOfMoving
  public HttpResponseHandle exchange(
      String endpoint,
      HttpMethodHandle method,
      HttpHeaderListHandle headers,
      InputDataStreamHandle<Byte> body
  ) throws HttpException {
    UriHandle uri = Uris.get(joinUrlGuide().map(baseUrl, endpoint));
    HttpRequestHandle request = HttpRequests.get(method, uri);
    return underlyingPort.exchange(request);
  }

  @Mapper
  @Override
  public String baseUrl() {
    return baseUrl;
  }
}
