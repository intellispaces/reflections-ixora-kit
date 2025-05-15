package tech.intellispaces.ixora.http.annotationprocessor;

import tech.intellispaces.annotationprocessor.ArtifactGeneratorContext;
import tech.intellispaces.ixora.http.MovableInboundHttpPort;
import tech.intellispaces.ixora.http.common.HttpNameConventionFunctions;
import tech.intellispaces.jstatements.customtype.CustomType;
import tech.intellispaces.reflections.framework.annotationprocessor.ReflectionsArtifactGenerator;
import tech.intellispaces.reflections.framework.annotation.Factory;
import tech.intellispaces.reflections.framework.naming.NameConventionFunctions;
import tech.intellispaces.reflections.framework.reflection.DownwardObjectFactory;

public class HttpPortFactoryGenerator extends ReflectionsArtifactGenerator {

  public HttpPortFactoryGenerator(CustomType portDomain) {
    super(portDomain);
  }

  @Override
  public boolean isRelevant(ArtifactGeneratorContext context) {
    return true;
  }

  @Override
  public String generatedArtifactName() {
    return HttpNameConventionFunctions.getPortFactoryCanonicalName(sourceArtifact());
  }

  @Override
  protected String templateName() {
    return "/http_port_factory.template";
  }

  @Override
  protected boolean analyzeSourceArtifact(ArtifactGeneratorContext context) {
    addImport(Factory.class);
    addImport(MovableInboundHttpPort.class);
    addImport(DownwardObjectFactory.class);

    String providerCustomizerSimpleName = addImportAndGetSimpleName(
        HttpNameConventionFunctions.getPortAssistantCustomizerCanonicalName(sourceArtifact())
    );
    String handleImplSimpleName = addImportAndGetSimpleName(
        HttpNameConventionFunctions.getPortReflectionImplCanonicalName(sourceArtifact())
    );
    String movableReflectionSimpleName = addImportAndGetSimpleName(
        NameConventionFunctions.getMovableReflectionTypeName(sourceArtifact().canonicalName(), true)
    );

    addVariable("providerCustomizerSimpleName", providerCustomizerSimpleName);
    addVariable("handleImplSimpleName", handleImplSimpleName);
    addVariable("movableReflectionSimpleName", movableReflectionSimpleName);
    return true;
  }
}
