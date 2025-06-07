package tech.intellispaces.ixora.internet.uri;

import java.net.URI;

import tech.intellispaces.reflections.framework.annotation.Mapper;
import tech.intellispaces.reflections.framework.annotation.Reflection;

@Reflection(domainClass = UriDomain.class)
public abstract class JavaUriReflection implements Uri {
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
