package tech.intellispaces.ixora.http.pathtree;

import tech.intellispaces.actions.Action2;
import tech.intellispaces.ixora.http.HttpRequest;
import tech.intellispaces.ixora.http.HttpResponseReflection;
import tech.intellispaces.javareflection.method.MethodSignature;
import tech.intellispaces.reflections.framework.action.TraverseActions;
import tech.intellispaces.reflections.framework.channel.Channel1;

public class FinalExecutor {
  private final Object port;
  private final Class<? extends Channel1> channelClass;
  private final MethodSignature method;
  private final Action2<HttpResponseReflection, Object, HttpRequest> action;

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

  public HttpResponseReflection exchange(Object port, HttpRequest request) {
    return action.execute(port, request);
  }

  @SuppressWarnings("unchecked, rawtypes")
  private Action2<HttpResponseReflection, Object, HttpRequest> makeAction(
      Object port, Class<? extends Channel1> channelClass
  ) {
    return (Action2) TraverseActions.mapOfMovingThruChannel1(port.getClass(), channelClass);
  }
}
