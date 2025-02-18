package tech.intellispaces.jaquarius.ixora.okhttp;

import okhttp3.Response;
import okhttp3.ResponseBody;
import tech.intellispaces.jaquarius.annotation.Mapper;
import tech.intellispaces.jaquarius.annotation.MapperOfMoving;
import tech.intellispaces.jaquarius.annotation.ObjectHandle;
import tech.intellispaces.jaquarius.ixora.data.stream.ByteStreams;
import tech.intellispaces.jaquarius.ixora.data.stream.MovableByteInputStreamHandle;
import tech.intellispaces.jaquarius.ixora.http.HttpStatusHandle;
import tech.intellispaces.jaquarius.ixora.http.HttpStatuses;

@ObjectHandle(OkHttpResponseDomain.class)
public abstract class OkHttpResponseHandleImpl implements UnmovableOkHttpResponseHandle {
  private final Response response;
  private final MovableByteInputStreamHandle bodyStream;

  OkHttpResponseHandleImpl(Response response) {
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
  public HttpStatusHandle status() {
    return HttpStatuses.get(response.code());
  }

  @MapperOfMoving
  @Override
  public MovableByteInputStreamHandle bodyStream() {
    return bodyStream;
  }
}
