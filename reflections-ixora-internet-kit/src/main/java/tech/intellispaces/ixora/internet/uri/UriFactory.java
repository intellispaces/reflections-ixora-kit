package tech.intellispaces.ixora.internet.uri;

import java.net.URI;

import tech.intellispaces.reflections.framework.annotation.Factory;

@Factory
public class UriFactory implements UriAssistantCustomizer {

  @Override
  public Uri create(String string) {
    return new JavaUriReflectionWrapper(URI.create(string));
  }
}
