package tech.intellispaces.jaquarius.ixora.http.port;

import tech.intellispaces.jaquarius.ixora.http.pathtree.PathSegment;

import java.util.List;

public class PortDescriptor {
  private final Object port;
  private final Class<?> portDomain;
  private List<PathSegment> rootSegments;

  public PortDescriptor(Object port, Class<?> portDomain) {
    this.port = port;
    this.portDomain = portDomain;
  }

  public Object port() {
    return port;
  }

  public Class<?> portDomain() {
    return portDomain;
  }

  public List<PathSegment> rootSegments() {
    return rootSegments;
  }

  public void setRootSegments(List<PathSegment> segments) {
    this.rootSegments = segments;
  }
}
