package tech.intellispaces.jaquarius.ixora.internet.uri;

import java.net.URI;

public interface Uris {

  static UriHandle get(String string) {
    return new JavaUriHandleWrapper(URI.create(string));
  }
}
