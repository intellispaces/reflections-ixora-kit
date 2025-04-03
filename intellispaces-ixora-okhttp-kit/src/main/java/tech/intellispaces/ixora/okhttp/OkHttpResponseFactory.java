package tech.intellispaces.ixora.okhttp;

import okhttp3.Response;
import tech.intellispaces.jaquarius.annotation.ObjectFactory;

@ObjectFactory
public class OkHttpResponseFactory implements OkHttpResponseAssistantExtension {

  @Override
  public UnmovableOkHttpResponseHandle handleOf(Response response) {
    return new OkHttpResponseImplWrapper(response);
  }
}
