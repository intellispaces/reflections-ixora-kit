package tech.intellispaces.ixora.http.annotation.processor;

import tech.intellispaces.commons.annotation.processor.ArtifactGeneratorContext;
import tech.intellispaces.commons.base.entity.Reference;
import tech.intellispaces.commons.java.reflection.customtype.CustomType;
import tech.intellispaces.ixora.http.HttpRequest;
import tech.intellispaces.ixora.http.HttpResponse;
import tech.intellispaces.ixora.http.MovableInboundHttpPort;
import tech.intellispaces.ixora.http.common.HttpNameConventionFunctions;
import tech.intellispaces.ixora.http.engine.HttpPortEngines;
import tech.intellispaces.ixora.http.exception.HttpException;
import tech.intellispaces.jaquarius.annotation.MapperOfMoving;
import tech.intellispaces.jaquarius.annotation.Mover;
import tech.intellispaces.jaquarius.annotation.ObjectHandle;
import tech.intellispaces.jaquarius.annotationprocessor.JaquariusArtifactGenerator;
import tech.intellispaces.jaquarius.naming.NameConventionFunctions;

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
    addImport(HttpResponse.class);
    addImport(HttpRequest.class);
    addImport(HttpException.class);
    addImport(MovableInboundHttpPort.class);
    addImport(Reference.class);
    addImport(HttpPortEngines.class);

    movableHandleSimpleName = addImportAndGetSimpleName(
        NameConventionFunctions.getMovablePureObjectTypename(sourceArtifact().canonicalName(), true)
    );

    addVariable("movableHandleSimpleName", movableHandleSimpleName);
    return true;
  }
}
