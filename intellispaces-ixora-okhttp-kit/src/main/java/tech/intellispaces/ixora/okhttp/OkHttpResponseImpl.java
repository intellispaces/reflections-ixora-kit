package tech.intellispaces.ixora.okhttp;

import okhttp3.Response;
import okhttp3.ResponseBody;
import tech.intellispaces.ixora.data.stream.ByteInputStreams;
import tech.intellispaces.ixora.data.stream.MovableByteInputStreamHandle;
import tech.intellispaces.ixora.http.HttpStatusHandle;
import tech.intellispaces.ixora.http.HttpStatuses;
import tech.intellispaces.jaquarius.annotation.Mapper;
import tech.intellispaces.jaquarius.annotation.MapperOfMoving;
import tech.intellispaces.jaquarius.annotation.ObjectHandle;

@ObjectHandle(OkHttpResponseDomain.class)
public abstract class OkHttpResponseImpl implements UnmovableOkHttpResponse, UnmovableOkHttpResponseHandle {
  private final Response response;
  private final MovableByteInputStreamHandle bodyStream;

  OkHttpResponseImpl(Response response) {
    this.response = response;

    ResponseBody body = response.body();
    this.bodyStream = (body != null ? ByteInputStreams.handleOf(body.byteStream()) : ByteInputStreams.empty());
  }

  public Response getResponse() {
    return response;
  }

  @Override
  public void unbind() {
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
