package tech.intellispaces.ixora.internet.uri;

import tech.intellispaces.jaquarius.annotation.ObjectFactory;

import java.net.URI;

@ObjectFactory
public class UriFactory implements UriAssistantExtension {

  @Override
  public UriHandle create(String string) {
    return new JavaUriHandleWrapper(URI.create(string));
  }
}
