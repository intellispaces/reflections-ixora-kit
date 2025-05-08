package tech.intellispaces.ixora.internet.uri;

import java.net.URI;

import tech.intellispaces.reflections.annotation.Factory;

@Factory
public class UriFactory implements UriAssistantCustomizer {

  @Override
  public UriHandle create(String string) {
    return new JavaUriHandleWrapper(URI.create(string));
  }
}
