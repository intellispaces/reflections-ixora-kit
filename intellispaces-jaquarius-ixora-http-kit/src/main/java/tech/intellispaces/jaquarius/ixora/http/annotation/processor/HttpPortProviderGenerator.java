package tech.intellispaces.jaquarius.ixora.http.annotation.processor;

import tech.intellispaces.commons.annotation.processor.ArtifactGeneratorContext;
import tech.intellispaces.commons.java.reflection.customtype.CustomType;
import tech.intellispaces.jaquarius.annotation.MapperOfMoving;
import tech.intellispaces.jaquarius.annotation.Mover;
import tech.intellispaces.jaquarius.annotation.ObjectHandle;
import tech.intellispaces.jaquarius.annotationprocessor.JaquariusArtifactGenerator;
import tech.intellispaces.jaquarius.ixora.http.HttpRequestHandle;
import tech.intellispaces.jaquarius.ixora.http.HttpResponseHandle;
import tech.intellispaces.jaquarius.ixora.http.MovableInboundHttpPortHandle;
import tech.intellispaces.jaquarius.ixora.http.common.HttpNameConventionFunctions;
import tech.intellispaces.jaquarius.ixora.http.exception.HttpException;
import tech.intellispaces.jaquarius.naming.NameConventionFunctions;

public class HttpPortProviderGenerator extends JaquariusArtifactGenerator {
  private String handleImplSimpleName;
  private String movableHandleSimpleName;

  public HttpPortProviderGenerator(CustomType entityType) {
    super(entityType);
  }

  @Override
  public boolean isRelevant(ArtifactGeneratorContext context) {
    return true;
  }

  @Override
  public String generatedArtifactName() {
    return HttpNameConventionFunctions.getPortProviderCanonicalName(sourceArtifact());
  }

  @Override
  protected String templateName() {
    return "/http_port_provider.template";
  }

  @Override
  protected boolean analyzeSourceArtifact(ArtifactGeneratorContext context) {
    addImport(ObjectHandle.class);
    addImport(Mover.class);
    addImport(MapperOfMoving.class);
    addImport(HttpResponseHandle.class);
    addImport(HttpRequestHandle.class);
    addImport(HttpException.class);
    addImport(MovableInboundHttpPortHandle.class);

    handleImplSimpleName = addImportAndGetSimpleName(
        HttpNameConventionFunctions.getPortHandleImplCanonicalName(sourceArtifact())
    );
    movableHandleSimpleName = addImportAndGetSimpleName(
        NameConventionFunctions.getMovableObjectHandleTypename(sourceArtifact().canonicalName())
    );

    addVariable("handleImplSimpleName", handleImplSimpleName);
    addVariable("movableHandleSimpleName", movableHandleSimpleName);
    return true;
  }
}
