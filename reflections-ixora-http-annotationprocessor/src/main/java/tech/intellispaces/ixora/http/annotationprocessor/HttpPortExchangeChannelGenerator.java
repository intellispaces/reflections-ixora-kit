package tech.intellispaces.ixora.http.annotationprocessor;

import tech.intellispaces.annotationprocessor.ArtifactGeneratorContext;
import tech.intellispaces.ixora.http.HttpRequestDomain;
import tech.intellispaces.ixora.http.HttpResponseDomain;
import tech.intellispaces.ixora.http.common.HttpNameConventionFunctions;
import tech.intellispaces.ixora.http.exception.HttpException;
import tech.intellispaces.javareflection.customtype.CustomType;
import tech.intellispaces.javareflection.method.MethodStatement;
import tech.intellispaces.reflections.framework.annotation.Channel;
import tech.intellispaces.reflections.framework.annotationprocessor.ReflectionsArtifactGenerator;
import tech.intellispaces.reflections.framework.space.channel.ChannelFunctions;

public class HttpPortExchangeChannelGenerator extends ReflectionsArtifactGenerator {
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
