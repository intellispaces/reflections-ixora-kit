package tech.intellispaces.ixora.internet.uri;

import tech.intellispaces.reflections.framework.annotation.Mapper;
import tech.intellispaces.reflections.framework.annotation.Reflection;

import java.net.URI;

@Reflection(UriDomain.class)
public abstract class JavaUriReflection implements UnmovableUri {
  private final URI uri;

  public JavaUriReflection(URI uri) {
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
