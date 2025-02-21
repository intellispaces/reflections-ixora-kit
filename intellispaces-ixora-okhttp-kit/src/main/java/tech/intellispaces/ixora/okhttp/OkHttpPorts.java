package tech.intellispaces.ixora.okhttp;

import okhttp3.OkHttpClient;

import java.util.concurrent.TimeUnit;

public interface OkHttpPorts {

  static MovableOkHttpPortHandle get() {
    return new OkHttpPortHandleImplWrapper(new OkHttpClient());
  }

  static MovableOkHttpPortHandle get(OkHttpPortSettingsHandle properties) {
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
    return new OkHttpPortHandleImplWrapper(builder.build());
  }
}
