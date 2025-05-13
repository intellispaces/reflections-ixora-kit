package tech.intellispaces.ixora.okhttp;

import okhttp3.Response;
import tech.intellispaces.reflections.framework.annotation.Factory;

@Factory
public class OkHttpResponseFactory implements OkHttpResponseAssistantCustomizer {

  @Override
  public UnmovableOkHttpResponseReflection handleOf(Response response) {
    return new OkHttpResponseImplWrapper(response);
  }
}
