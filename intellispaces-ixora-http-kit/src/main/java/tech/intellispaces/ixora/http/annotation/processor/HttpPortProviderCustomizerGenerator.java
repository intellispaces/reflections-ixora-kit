package tech.intellispaces.ixora.http.annotation.processor;

import tech.intellispaces.commons.annotation.processor.ArtifactGeneratorContext;
import tech.intellispaces.commons.reflection.customtype.CustomType;
import tech.intellispaces.ixora.http.MovableInboundHttpPort;
import tech.intellispaces.ixora.http.common.HttpNameConventionFunctions;
import tech.intellispaces.jaquarius.annotation.ArtifactCustomizer;
import tech.intellispaces.jaquarius.annotationprocessor.JaquariusArtifactGenerator;
import tech.intellispaces.jaquarius.naming.NameConventionFunctions;

public class HttpPortProviderCustomizerGenerator extends JaquariusArtifactGenerator {

  public HttpPortProviderCustomizerGenerator(CustomType portDomain) {
    super(portDomain);
  }

  @Override
  public boolean isRelevant(ArtifactGeneratorContext context) {
    return true;
  }

  @Override
  public String generatedArtifactName() {
    return HttpNameConventionFunctions.getPortProviderCustomizerCanonicalName(sourceArtifact());
  }

  @Override
  protected String templateName() {
    return "/http_port_provider_customizer.template";
  }

  @Override
  protected boolean analyzeSourceArtifact(ArtifactGeneratorContext context) {
    addImport(ArtifactCustomizer.class);
    addImport(MovableInboundHttpPort.class);

    String movableHandleSimpleName = addImportAndGetSimpleName(
        NameConventionFunctions.getMovableObjectHandleTypename(sourceArtifact().canonicalName(), true)
    );
    addVariable("movableHandleSimpleName", movableHandleSimpleName);
    return true;
  }
}
