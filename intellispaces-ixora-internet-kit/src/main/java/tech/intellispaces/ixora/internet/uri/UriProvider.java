package tech.intellispaces.ixora.internet.uri;

import tech.intellispaces.jaquarius.annotation.ObjectProvider;

import java.net.URI;

@ObjectProvider
public class UriProvider implements UriProviderCustomizer {

  @Override
  public UriHandle create(String string) {
    return new JavaUriHandleWrapper(URI.create(string));
  }
}
