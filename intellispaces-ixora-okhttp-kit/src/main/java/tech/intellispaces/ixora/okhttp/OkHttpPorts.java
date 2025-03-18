package tech.intellispaces.ixora.okhttp;

import okhttp3.OkHttpClient;

import java.util.concurrent.TimeUnit;

public interface OkHttpPorts {

  static MovableOkHttpPortHandle get() {
    return new OkHttpPortImplWrapper(new OkHttpClient());
  }

  static MovableOkHttpPortHandle get(OkHttpPortSettings properties) {
    var builder = new OkHttpClient().newBuilder();
    builder.connectTimeout(properties.connectTimeoutMs(), TimeUnit.MILLISECONDS);
    builder.readTimeout(properties.readTimeoutMs(), TimeUnit.MILLISECONDS);
    builder.writeTimeout(properties.writeTimeoutMs(), TimeUnit.MILLISECONDS);
    return new OkHttpPortImplWrapper(builder.build());
  }
}
