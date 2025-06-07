package tech.intellispaces.ixora.http.engine;

import java.util.List;

import tech.intellispaces.actions.conditional.ConditionalActions;
import tech.intellispaces.commons.abstraction.Reference;
import tech.intellispaces.commons.abstraction.References;
import tech.intellispaces.commons.exception.NotImplementedExceptions;
import tech.intellispaces.commons.type.Classes;
import tech.intellispaces.ixora.http.HttpRequest;
import tech.intellispaces.ixora.http.HttpResponse;
import tech.intellispaces.ixora.http.pathtree.FinalExecutor;
import tech.intellispaces.ixora.http.pathtree.PathSegment;
import tech.intellispaces.ixora.http.pathtree.PathTreeFunctions;
import tech.intellispaces.ixora.http.port.PortDescriptor;

public class DefaultHttpPortEngine implements HttpPortEngine {

  @Override
  public Reference<?> bridge(Object port, Class<?> portDomain) {
    return References.create(new PortDescriptor(port, portDomain));
  }

  @Override
  public HttpResponse exchange(Reference<?> reference, HttpRequest request) {
    var descriptor = (PortDescriptor) reference.get();

    List<PathSegment> rootSegments = ConditionalActions.getOrSetIfAbsentAction(
        PortDescriptor.class, Classes.ofList(PathSegment.class)
    ).execute(
        descriptor,
        PortDescriptor::rootSegments,
        PortDescriptor::setRootSegments,
        () -> PathTreeFunctions.readPathTree(descriptor.port(), descriptor.portDomain())
    );

    FinalExecutor executor = PathTreeFunctions.findExecution(request, rootSegments);
    if (executor == null) {
      throw NotImplementedExceptions.withCode("YNScfA");
    }
    return executor.exchange(descriptor.port(), request);
  }
}
