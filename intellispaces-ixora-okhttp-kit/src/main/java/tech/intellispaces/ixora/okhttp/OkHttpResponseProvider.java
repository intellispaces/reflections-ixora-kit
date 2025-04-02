package tech.intellispaces.ixora.okhttp;

import okhttp3.Response;
import tech.intellispaces.jaquarius.annotation.ObjectProvider;

@ObjectProvider
public class OkHttpResponseProvider implements OkHttpResponseProviderCustomizer {

  @Override
  public UnmovableOkHttpResponseHandle handleOf(Response response) {
    return new OkHttpResponseImplWrapper(response);
  }
}
