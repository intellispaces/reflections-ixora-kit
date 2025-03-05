package tech.intellispaces.ixora.okhttp;

import okhttp3.Response;
import okhttp3.ResponseBody;
import tech.intellispaces.ixora.data.stream.ByteStreams;
import tech.intellispaces.ixora.data.stream.MovableByteInputStream;
import tech.intellispaces.ixora.http.HttpStatus;
import tech.intellispaces.ixora.http.HttpStatuses;
import tech.intellispaces.jaquarius.annotation.Mapper;
import tech.intellispaces.jaquarius.annotation.MapperOfMoving;
import tech.intellispaces.jaquarius.annotation.ObjectHandle;
import tech.intellispaces.jaquarius.object.reference.UnmovableObjectHandle;

@ObjectHandle(OkHttpResponseDomain.class)
public abstract class OkHttpResponseImpl implements UnmovableOkHttpResponse, UnmovableObjectHandle<OkHttpResponseDomain> {
  private final Response response;
  private final MovableByteInputStream bodyStream;

  OkHttpResponseImpl(Response response) {
    this.response = response;

    ResponseBody body = response.body();
    this.bodyStream = (body != null ? ByteStreams.get(body.byteStream()) : ByteStreams.empty());
  }

  public Response getResponse() {
    return response;
  }

  @Override
  public void release() {
    response.close();
  }

  @Mapper
  @Override
  public HttpStatus status() {
    return HttpStatuses.get(response.code());
  }

  @MapperOfMoving
  @Override
  public MovableByteInputStream bodyStream() {
    return bodyStream;
  }
}
