package tech.intellispaces.ixora.internet.uri;

import java.net.URI;

public interface Uris {

  static UriHandle get(String string) {
    return new JavaUriHandleWrapper(URI.create(string));
  }
}
