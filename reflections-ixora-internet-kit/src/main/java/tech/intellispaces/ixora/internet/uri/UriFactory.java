package tech.intellispaces.ixora.internet.uri;

import java.net.URI;

import tech.intellispaces.reflections.framework.annotation.Factory;

@Factory
public class UriFactory implements UriAssistantCustomizer {

  @Override
  public UriReflection create(String string) {
    return new JavaUriReflectionImplWrapper(URI.create(string));
  }
}
