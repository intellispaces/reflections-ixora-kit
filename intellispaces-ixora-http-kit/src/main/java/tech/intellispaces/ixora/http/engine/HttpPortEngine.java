package tech.intellispaces.ixora.http.engine;

import tech.intellispaces.commons.base.entity.Reference;
import tech.intellispaces.ixora.http.HttpRequestHandle;
import tech.intellispaces.ixora.http.HttpResponseHandle;

public interface HttpPortEngine {

  Reference<?> bridge(Object port, Class<?> portDomain);

  HttpResponseHandle exchange(Reference<?> bridge, HttpRequestHandle request);
}
