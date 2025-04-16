package tech.intellispaces.ixora.http.engine;

import tech.intellispaces.commons.entity.Reference;
import tech.intellispaces.ixora.http.HttpRequest;
import tech.intellispaces.ixora.http.HttpResponseHandle;

public interface HttpPortEngine {

  Reference<?> bridge(Object port, Class<?> portDomain);

  HttpResponseHandle exchange(Reference<?> bridge, HttpRequest request);
}
