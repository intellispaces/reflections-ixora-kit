package tech.intellispaces.ixora.okhttp;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

import tech.intellispaces.reflections.framework.annotation.Factory;

@Factory
public class OkHttpPortFactory implements OkHttpPortAssistantCustomizer {

  @Override
  public MovableOkHttpPortReflection create() {
    return new OkHttpPortReflectionImplWrapper(new OkHttpClient());
  }

  @Override
  public MovableOkHttpPortReflection create(OkHttpPortSettings properties) {
    var builder = new OkHttpClient().newBuilder();
    builder.connectTimeout(properties.connectTimeoutMs(), TimeUnit.MILLISECONDS);
    builder.readTimeout(properties.readTimeoutMs(), TimeUnit.MILLISECONDS);
    builder.writeTimeout(properties.writeTimeoutMs(), TimeUnit.MILLISECONDS);
    return new OkHttpPortReflectionImplWrapper(builder.build());
  }
}
