package tech.intellispaces.ixora.http.annotationprocessor;

import tech.intellispaces.annotationprocessor.ArtifactGeneratorContext;
import tech.intellispaces.ixora.http.MovableInboundHttpPort;
import tech.intellispaces.ixora.http.common.HttpNameConventionFunctions;
import tech.intellispaces.jstatements.customtype.CustomType;
import tech.intellispaces.reflections.annotation.AssistantCustomizer;
import tech.intellispaces.reflections.annotationprocessor.JaquariusArtifactGenerator;
import tech.intellispaces.reflections.artifact.ArtifactTypes;
import tech.intellispaces.reflections.naming.NameConventionFunctions;
import tech.intellispaces.reflections.object.reference.DownwardObjectFactory;

public class HttpPortAssistantCustomizerGenerator extends JaquariusArtifactGenerator {

  public HttpPortAssistantCustomizerGenerator(CustomType portDomain) {
    super(portDomain);
  }

  @Override
  public boolean isRelevant(ArtifactGeneratorContext context) {
    return true;
  }

  @Override
  public String generatedArtifactName() {
    return HttpNameConventionFunctions.getPortAssistantCustomizerCanonicalName(sourceArtifact());
  }

  @Override
  protected String templateName() {
    return "/http_port_assistant_customizer.template";
  }

  @Override
  protected boolean analyzeSourceArtifact(ArtifactGeneratorContext context) {
    addImport(ArtifactTypes.class);
    addImport(AssistantCustomizer.class);
    addImport(MovableInboundHttpPort.class);
    addImport(DownwardObjectFactory.class);

    String movableHandleSimpleName = addImportAndGetSimpleName(
        NameConventionFunctions.getMovableObjectHandleTypename(sourceArtifact().canonicalName(), true)
    );
    addVariable("movableHandleSimpleName", movableHandleSimpleName);
    return true;
  }
}
