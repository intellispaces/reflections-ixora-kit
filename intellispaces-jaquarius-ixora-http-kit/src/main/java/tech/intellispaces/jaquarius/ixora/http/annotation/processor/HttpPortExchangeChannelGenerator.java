package tech.intellispaces.jaquarius.ixora.http.annotation.processor;

import tech.intellispaces.commons.annotation.processor.ArtifactGeneratorContext;
import tech.intellispaces.commons.java.reflection.customtype.CustomType;
import tech.intellispaces.commons.java.reflection.method.MethodStatement;
import tech.intellispaces.jaquarius.annotation.Channel;
import tech.intellispaces.jaquarius.annotationprocessor.JaquariusArtifactGenerator;
import tech.intellispaces.jaquarius.ixora.http.HttpRequestDomain;
import tech.intellispaces.jaquarius.ixora.http.HttpResponseDomain;
import tech.intellispaces.jaquarius.ixora.http.common.HttpNameConventionFunctions;
import tech.intellispaces.jaquarius.ixora.http.exception.HttpException;
import tech.intellispaces.jaquarius.space.channel.ChannelFunctions;

public class HttpPortExchangeChannelGenerator extends JaquariusArtifactGenerator {
  private final CustomType portDomain;
  private final CustomType ontology;
  private final MethodStatement channelMethod;
  private String cid;
  private String superChannelSimpleName;

  public HttpPortExchangeChannelGenerator(
      CustomType portDomain, CustomType ontology, MethodStatement channelMethod
  ) {
    super(ontology);
    this.portDomain = portDomain;
    this.ontology = ontology;
    this.channelMethod = channelMethod;
  }

  @Override
  public boolean isRelevant(ArtifactGeneratorContext context) {
    return true;
  }

  @Override
  public String generatedArtifactName() {
    return HttpNameConventionFunctions.getActualPortExchangeChannelCanonicalName(portDomain, ontology, channelMethod);
  }

  @Override
  protected String templateName() {
    return "/http_port_exchange_channel.template";
  }

  @Override
  protected boolean analyzeSourceArtifact(ArtifactGeneratorContext context) {
    addImport(Channel.class);
    addImport(HttpRequestDomain.class);
    addImport(HttpResponseDomain.class);
    addImport(HttpException.class);

    cid = ChannelFunctions.computedChannelId(
        HttpNameConventionFunctions.getFormalPortExchangeChannelCanonicalName(portDomain, ontology, channelMethod)
    );
    superChannelSimpleName = addImportAndGetSimpleName(
        HttpNameConventionFunctions.getPortExchangeChannelCanonicalName(portDomain)
    );

    addVariable("cid", cid);
    addVariable("superChannelSimpleName", superChannelSimpleName);
    addVariable("channelMethodName", channelMethod.name());
    addVariable("portSimpleName", addImportAndGetSimpleName(portDomain.canonicalName()));
    addVariable("ontologySimpleName", addImportAndGetSimpleName(ontology.canonicalName()));
    return true;
  }
}
