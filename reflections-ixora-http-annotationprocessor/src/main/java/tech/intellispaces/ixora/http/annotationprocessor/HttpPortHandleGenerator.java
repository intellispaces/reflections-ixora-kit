package tech.intellispaces.ixora.http.annotationprocessor;

import tech.intellispaces.annotationprocessor.ArtifactGeneratorContext;
import tech.intellispaces.commons.abstraction.Reference;
import tech.intellispaces.ixora.http.HttpRequest;
import tech.intellispaces.ixora.http.HttpResponseHandle;
import tech.intellispaces.ixora.http.MovableInboundHttpPort;
import tech.intellispaces.ixora.http.common.HttpNameConventionFunctions;
import tech.intellispaces.ixora.http.engine.HttpPortEngines;
import tech.intellispaces.ixora.http.exception.HttpException;
import tech.intellispaces.jstatements.customtype.CustomType;
import tech.intellispaces.reflections.annotationprocessor.JaquariusArtifactGenerator;
import tech.intellispaces.reflections.framework.annotation.MapperOfMoving;
import tech.intellispaces.reflections.framework.annotation.Mover;
import tech.intellispaces.reflections.framework.annotation.ObjectHandle;
import tech.intellispaces.reflections.framework.naming.NameConventionFunctions;
import tech.intellispaces.reflections.framework.object.reference.DownwardObjectFactory;
import tech.intellispaces.reflections.framework.object.reference.MovableObjectHandle;

public class HttpPortHandleGenerator extends JaquariusArtifactGenerator {
  private String movableHandleSimpleName;

  public HttpPortHandleGenerator(CustomType entityType) {
    super(entityType);
  }

  @Override
  public boolean isRelevant(ArtifactGeneratorContext context) {
    return true;
  }

  @Override
  public String generatedArtifactName() {
    return HttpNameConventionFunctions.getPortHandleCanonicalName(sourceArtifact());
  }

  @Override
  protected String templateName() {
    return "/http_port_handle.template";
  }

  @Override
  protected boolean analyzeSourceArtifact(ArtifactGeneratorContext context) {
    addImport(ObjectHandle.class);
    addImport(Mover.class);
    addImport(MapperOfMoving.class);
    addImport(HttpResponseHandle.class);
    addImport(HttpRequest.class);
    addImport(HttpException.class);
    addImport(MovableObjectHandle.class);
    addImport(MovableInboundHttpPort.class);
    addImport(DownwardObjectFactory.class);
    addImport(Reference.class);
    addImport(HttpPortEngines.class);

    movableHandleSimpleName = addImportAndGetSimpleName(
        NameConventionFunctions.getMovableObjectHandleTypename(sourceArtifact().canonicalName(), true)
    );

    addVariable("movableHandleSimpleName", movableHandleSimpleName);
    return true;
  }
}
