package tech.intellispaces.ixora.http.annotationprocessor;

import tech.intellispaces.annotationprocessor.ArtifactGeneratorContext;
import tech.intellispaces.ixora.http.MovableInboundHttpPort;
import tech.intellispaces.ixora.http.common.HttpNameConventionFunctions;
import tech.intellispaces.javareflection.customtype.CustomType;
import tech.intellispaces.reflections.framework.annotation.AssistantCustomizer;
import tech.intellispaces.reflections.framework.annotationprocessor.ReflectionsArtifactGenerator;
import tech.intellispaces.reflections.framework.artifact.ArtifactTypes;
import tech.intellispaces.reflections.framework.naming.NameConventionFunctions;
import tech.intellispaces.reflections.framework.reflection.DownwardObjectFactory;

public class HttpPortAssistantCustomizerGenerator extends ReflectionsArtifactGenerator {

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

    String movableReflectionSimpleName = addImportAndGetSimpleName(
        NameConventionFunctions.getMovableReflectionTypeName(sourceArtifact().canonicalName(), true)
    );
    addVariable("movableReflectionSimpleName", movableReflectionSimpleName);
    return true;
  }
}
