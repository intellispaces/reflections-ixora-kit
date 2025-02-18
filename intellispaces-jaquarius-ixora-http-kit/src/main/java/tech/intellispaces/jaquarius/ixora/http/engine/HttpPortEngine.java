package tech.intellispaces.jaquarius.ixora.http.engine;

import tech.intellispaces.commons.base.entity.Reference;
import tech.intellispaces.jaquarius.ixora.http.HttpRequestHandle;
import tech.intellispaces.jaquarius.ixora.http.HttpResponseHandle;

public interface HttpPortEngine {

  Reference<?> bridge(Object port, Class<?> portDomain);

  HttpResponseHandle exchange(Reference<?> bridge, HttpRequestHandle request);
}
