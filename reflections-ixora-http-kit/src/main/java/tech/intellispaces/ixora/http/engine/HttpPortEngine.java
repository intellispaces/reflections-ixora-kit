package tech.intellispaces.ixora.http.engine;

import tech.intellispaces.commons.abstraction.Reference;
import tech.intellispaces.ixora.http.HttpRequest;
import tech.intellispaces.ixora.http.HttpResponseReflection;

public interface HttpPortEngine {

  Reference<?> bridge(Object port, Class<?> portDomain);

  HttpResponseReflection exchange(Reference<?> bridge, HttpRequest request);
}
