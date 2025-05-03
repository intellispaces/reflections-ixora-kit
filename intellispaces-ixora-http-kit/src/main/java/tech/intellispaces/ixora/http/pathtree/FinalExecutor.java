package tech.intellispaces.ixora.http.pathtree;

import tech.intellispaces.actions.Action2;
import tech.intellispaces.ixora.http.HttpRequest;
import tech.intellispaces.ixora.http.HttpResponseHandle;
import tech.intellispaces.jstatements.method.MethodSignature;
import tech.intellispaces.reflections.action.TraverseActions;
import tech.intellispaces.reflections.channel.Channel1;

public class FinalExecutor {
  private final Object port;
  private final Class<? extends Channel1> channelClass;
  private final MethodSignature method;
  private final Action2<HttpResponseHandle, Object, HttpRequest> action;

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

  public HttpResponseHandle exchange(Object port, HttpRequest request) {
    return action.execute(port, request);
  }

  @SuppressWarnings("unchecked, rawtypes")
  private Action2<HttpResponseHandle, Object, HttpRequest> makeAction(
      Object port, Class<? extends Channel1> channelClass
  ) {
    return (Action2) TraverseActions.mapOfMovingThruChannel1(port.getClass(), channelClass);
  }
}
