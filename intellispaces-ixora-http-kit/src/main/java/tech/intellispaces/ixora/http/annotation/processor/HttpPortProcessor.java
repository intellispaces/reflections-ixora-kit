package tech.intellispaces.ixora.http.annotation.processor;

import tech.intellispaces.commons.annotation.processor.ArtifactGenerator;
import tech.intellispaces.commons.annotation.processor.ArtifactGeneratorContext;
import tech.intellispaces.commons.annotation.processor.ArtifactProcessor;
import tech.intellispaces.commons.annotation.processor.ArtifactValidator;
import tech.intellispaces.commons.reflection.customtype.CustomType;
import tech.intellispaces.commons.reflection.method.MethodStatement;
import tech.intellispaces.ixora.http.annotation.HttpPort;
import tech.intellispaces.jaquarius.annotationprocessor.AnnotationFunctions;
import tech.intellispaces.jaquarius.annotationprocessor.JaquariusArtifactProcessor;

import javax.lang.model.element.ElementKind;
import java.util.ArrayList;
import java.util.List;

public class HttpPortProcessor extends ArtifactProcessor {

  public HttpPortProcessor() {
    super(ElementKind.INTERFACE, HttpPort.class, JaquariusArtifactProcessor.SOURCE_VERSION);
  }

  @Override
  public boolean isApplicable(CustomType entityType) {
    return AnnotationFunctions.isAutoGenerationEnabled(entityType);
  }

  @Override
  public ArtifactValidator validator() {
    return null;
  }

  @Override
  public List<ArtifactGenerator> makeGenerators(CustomType portDomain, ArtifactGeneratorContext context) {
    List<ArtifactGenerator> generators = new ArrayList<>();
    generators.add(new HttpPortHandleGenerator(portDomain));
    generators.add(new HttpPortFactoryGenerator(portDomain));
    generators.add(new HttpPortAssistantExtensionGenerator(portDomain));

    List<CustomType> ontologies = portDomain.selectAnnotation(HttpPort.class.getCanonicalName()).orElseThrow()
        .value().orElseThrow()
        .asArray().orElseThrow()
        .elements().stream()
        .map(e -> e.asClass().orElseThrow().type())
        .toList();
    for (CustomType ontology : ontologies) {
      generators.add(new HttpPortGuideGenerator(portDomain, ontology));
      addHttpPortExchangeChannels(generators, portDomain, ontology);
    }
    return generators;
  }

  private void addHttpPortExchangeChannels(
      List<ArtifactGenerator> generators,
      CustomType portDomain,
      CustomType ontology
  ) {
    ontology.actualMethods().forEach(m -> addHttpPortExchangeChannels(generators, portDomain, ontology, m));
  }

  private void addHttpPortExchangeChannels(
      List<ArtifactGenerator> generators,
      CustomType portDomain,
      CustomType ontology,
      MethodStatement channelMethod
  ) {
    generators.add(
      new HttpPortExchangeChannelGenerator(portDomain, ontology, channelMethod)
    );
  }
}
