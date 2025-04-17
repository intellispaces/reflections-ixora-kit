package tech.intellispaces.ixora.internet.uri;

import tech.intellispaces.jaquarius.annotation.Factory;

import java.net.URI;

@Factory
public class UriFactory implements UriAssistantCustomizer {

  @Override
  public UriHandle create(String string) {
    return new JavaUriHandleWrapper(URI.create(string));
  }
}
