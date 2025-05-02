package tech.intellispaces.ixora.http.annotationprocessor;

import tech.intellispaces.annotationprocessor.ArtifactGeneratorContext;
import tech.intellispaces.ixora.http.MovableInboundHttpPort;
import tech.intellispaces.ixora.http.common.HttpNameConventionFunctions;
import tech.intellispaces.jaquarius.annotation.Factory;
import tech.intellispaces.jaquarius.annotationprocessor.JaquariusArtifactGenerator;
import tech.intellispaces.jaquarius.naming.NameConventionFunctions;
import tech.intellispaces.jaquarius.object.reference.DownwardObjectFactory;
import tech.intellispaces.statementsj.customtype.CustomType;

public class HttpPortFactoryGenerator extends JaquariusArtifactGenerator {

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
        HttpNameConventionFunctions.getPortHandleImplCanonicalName(sourceArtifact())
    );
    String movableHandleSimpleName = addImportAndGetSimpleName(
        NameConventionFunctions.getMovableObjectHandleTypename(sourceArtifact().canonicalName(), true)
    );

    addVariable("providerCustomizerSimpleName", providerCustomizerSimpleName);
    addVariable("handleImplSimpleName", handleImplSimpleName);
    addVariable("movableHandleSimpleName", movableHandleSimpleName);
    return true;
  }
}
