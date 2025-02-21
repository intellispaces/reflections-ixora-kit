package tech.intellispaces.ixora.http.pathtree;

import tech.intellispaces.commons.action.Action2;
import tech.intellispaces.commons.java.reflection.method.MethodSignature;
import tech.intellispaces.jaquarius.action.TraverseActions;
import tech.intellispaces.jaquarius.channel.Channel1;
import tech.intellispaces.ixora.http.HttpRequestHandle;
import tech.intellispaces.ixora.http.HttpResponseHandle;

public class FinalExecutor {
  private final Object port;
  private final Class<? extends Channel1> channelClass;
  private final MethodSignature method;
  private final Action2<HttpResponseHandle, Object, HttpRequestHandle> action;

  public FinalExecutor(
      Object port,
      Class<? extends Channel1> channelClass,
      MethodSignature method
  ) {
    this.port = port;
    this.channelClass = channelClass;
    this.method = method;
    this.action = makeAction(port, channelClass);
  }

  public MethodSignature method() {
    return method;
  }

  public HttpResponseHandle exchange(Object port, HttpRequestHandle request) {
    return action.execute(port, request);
  }

  @SuppressWarnings("unchecked, rawtypes")
  private Action2<HttpResponseHandle, Object, HttpRequestHandle> makeAction(
      Object port, Class<? extends Channel1> channelClass
  ) {
    return (Action2) TraverseActions.mapOfMovingThruChannel1(port.getClass(), channelClass);
  }
}
