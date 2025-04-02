package tech.intellispaces.ixora.okhttp;

import okhttp3.OkHttpClient;
import tech.intellispaces.jaquarius.annotation.ObjectProvider;

import java.util.concurrent.TimeUnit;

@ObjectProvider
public class OkHttpPortProvider implements OkHttpPortProviderCustomizer {

  @Override
  public MovableOkHttpPortHandle create() {
    return new OkHttpPortImplWrapper(new OkHttpClient());
  }

  @Override
  public MovableOkHttpPortHandle create(OkHttpPortSettings properties) {
    var builder = new OkHttpClient().newBuilder();
    builder.connectTimeout(properties.connectTimeoutMs(), TimeUnit.MILLISECONDS);
    builder.readTimeout(properties.readTimeoutMs(), TimeUnit.MILLISECONDS);
    builder.writeTimeout(properties.writeTimeoutMs(), TimeUnit.MILLISECONDS);
    return new OkHttpPortImplWrapper(builder.build());
  }
}
