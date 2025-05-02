package tech.intellispaces.ixora.http.annotationprocessor;

import tech.intellispaces.annotationprocessor.ArtifactGeneratorContext;
import tech.intellispaces.commons.abstraction.Reference;
import tech.intellispaces.ixora.http.HttpRequest;
import tech.intellispaces.ixora.http.HttpResponseHandle;
import tech.intellispaces.ixora.http.MovableInboundHttpPort;
import tech.intellispaces.ixora.http.common.HttpNameConventionFunctions;
import tech.intellispaces.ixora.http.engine.HttpPortEngines;
import tech.intellispaces.ixora.http.exception.HttpException;
import tech.intellispaces.jaquarius.annotation.MapperOfMoving;
import tech.intellispaces.jaquarius.annotation.Mover;
import tech.intellispaces.jaquarius.annotation.ObjectHandle;
import tech.intellispaces.jaquarius.annotationprocessor.JaquariusArtifactGenerator;
import tech.intellispaces.jaquarius.naming.NameConventionFunctions;
import tech.intellispaces.jaquarius.object.reference.DownwardObjectFactory;
import tech.intellispaces.jaquarius.object.reference.MovableObjectHandle;
import tech.intellispaces.statementsj.customtype.CustomType;

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
