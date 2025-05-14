package tech.intellispaces.ixora.internet.uri;

import tech.intellispaces.reflections.framework.annotation.Factory;

import java.net.URI;

@Factory
public class UriFactory implements UriAssistantCustomizer {

  @Override
  public UriReflection create(String string) {
    return new JavaUriReflectionImplWrapper(URI.create(string));
  }
}
