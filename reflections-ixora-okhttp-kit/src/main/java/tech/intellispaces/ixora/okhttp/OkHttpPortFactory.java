package tech.intellispaces.ixora.okhttp;

import okhttp3.OkHttpClient;
import tech.intellispaces.reflections.framework.annotation.Factory;

import java.util.concurrent.TimeUnit;

@Factory
public class OkHttpPortFactory implements OkHttpPortAssistantCustomizer {

  @Override
  public MovableOkHttpPortReflection create() {
    return new OkHttpPortImplWrapper(new OkHttpClient());
  }

  @Override
  public MovableOkHttpPortReflection create(OkHttpPortSettings properties) {
    var builder = new OkHttpClient().newBuilder();
    builder.connectTimeout(properties.connectTimeoutMs(), TimeUnit.MILLISECONDS);
    builder.readTimeout(properties.readTimeoutMs(), TimeUnit.MILLISECONDS);
    builder.writeTimeout(properties.writeTimeoutMs(), TimeUnit.MILLISECONDS);
    return new OkHttpPortImplWrapper(builder.build());
  }
}
