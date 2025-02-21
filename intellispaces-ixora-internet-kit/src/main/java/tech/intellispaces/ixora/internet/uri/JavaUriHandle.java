package tech.intellispaces.ixora.internet.uri;

import tech.intellispaces.jaquarius.annotation.Mapper;
import tech.intellispaces.jaquarius.annotation.ObjectHandle;

import java.net.URI;

@ObjectHandle(UriDomain.class)
public abstract class JavaUriHandle implements UnmovableUriHandle {
  private final URI uri;

  public JavaUriHandle(URI uri) {
    this.uri = uri;
  }

  @Mapper
  @Override
  public String path() {
    return uri.getPath();
  }

  @Mapper
  @Override
  public String query() {
    return uri.getQuery();
  }

  @Mapper
  @Override
  public String toString() {
    return uri.toString();
  }
}
