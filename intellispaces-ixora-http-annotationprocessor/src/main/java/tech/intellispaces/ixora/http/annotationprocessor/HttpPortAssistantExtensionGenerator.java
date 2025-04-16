package tech.intellispaces.ixora.http.annotationprocessor;

import tech.intellispaces.annotationprocessor.ArtifactGeneratorContext;
import tech.intellispaces.ixora.http.MovableInboundHttpPort;
import tech.intellispaces.ixora.http.common.HttpNameConventionFunctions;
import tech.intellispaces.jaquarius.annotation.AssistantExtension;
import tech.intellispaces.jaquarius.annotationprocessor.JaquariusArtifactGenerator;
import tech.intellispaces.jaquarius.artifact.ArtifactTypes;
import tech.intellispaces.jaquarius.naming.NameConventionFunctions;
import tech.intellispaces.jaquarius.object.reference.DownwardObjectFactory;
import tech.intellispaces.reflection.customtype.CustomType;

public class HttpPortAssistantExtensionGenerator extends JaquariusArtifactGenerator {

  public HttpPortAssistantExtensionGenerator(CustomType portDomain) {
    super(portDomain);
  }

  @Override
  public boolean isRelevant(ArtifactGeneratorContext context) {
    return true;
  }

  @Override
  public String generatedArtifactName() {
    return HttpNameConventionFunctions.getPortAssistantExtensionCanonicalName(sourceArtifact());
  }

  @Override
  protected String templateName() {
    return "/http_port_assistant_extension.template";
  }

  @Override
  protected boolean analyzeSourceArtifact(ArtifactGeneratorContext context) {
    addImport(ArtifactTypes.class);
    addImport(AssistantExtension.class);
    addImport(MovableInboundHttpPort.class);
    addImport(DownwardObjectFactory.class);

    String movableHandleSimpleName = addImportAndGetSimpleName(
        NameConventionFunctions.getMovableObjectHandleTypename(sourceArtifact().canonicalName(), true)
    );
    addVariable("movableHandleSimpleName", movableHandleSimpleName);
    return true;
  }
}
