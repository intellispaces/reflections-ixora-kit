package tech.intellispaces.ixora.okhttp;

import okhttp3.OkHttpClient;

import java.util.concurrent.TimeUnit;

public interface OkHttpPorts {

  static MovableOkHttpPort get() {
    return new OkHttpPortImplWrapper(new OkHttpClient());
  }

  static MovableOkHttpPort get(OkHttpPortSettings properties) {
    var builder = new OkHttpClient().newBuilder();
    if (properties.connectTimeoutMs() != null) {
      builder.connectTimeout(properties.connectTimeoutMs(), TimeUnit.MILLISECONDS);
    }
    if (properties.readTimeoutMs() != null) {
      builder.readTimeout(properties.readTimeoutMs(), TimeUnit.MILLISECONDS);
    }
    if (properties.writeTimeoutMs() != null) {
       builder.writeTimeout(properties.writeTimeoutMs(), TimeUnit.MILLISECONDS);
    }
    return new OkHttpPortImplWrapper(builder.build());
  }
}
