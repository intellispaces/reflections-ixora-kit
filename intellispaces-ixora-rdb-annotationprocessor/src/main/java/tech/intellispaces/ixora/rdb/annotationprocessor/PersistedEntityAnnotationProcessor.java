package tech.intellispaces.ixora.rdb.annotationprocessor;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Processor;
import javax.lang.model.element.ElementKind;

import com.google.auto.service.AutoService;

import tech.intellispaces.annotationprocessor.ArtifactGenerator;
import tech.intellispaces.annotationprocessor.ArtifactGeneratorContext;
import tech.intellispaces.annotationprocessor.ArtifactProcessor;
import tech.intellispaces.annotationprocessor.ArtifactValidator;
import tech.intellispaces.ixora.rdb.annotation.PersistedEntity;
import tech.intellispaces.jstatements.customtype.CustomType;
import tech.intellispaces.reflections.annotationprocessor.AnnotationFunctions;
import tech.intellispaces.reflections.annotationprocessor.JaquariusArtifactProcessor;

@AutoService(Processor.class)
public class PersistedEntityAnnotationProcessor extends ArtifactProcessor {

  public PersistedEntityAnnotationProcessor() {
    super(ElementKind.INTERFACE, PersistedEntity.class, JaquariusArtifactProcessor.SOURCE_VERSION);
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
  public List<ArtifactGenerator> makeGenerators(CustomType entityType, ArtifactGeneratorContext context) {
    var generators = new ArrayList<ArtifactGenerator>();
    generators.add(new EntityCrudOntologyGenerator(entityType));
    generators.add(new EntityCrudGuideGenerator(entityType));
    generators.add(new EntityCrudGuideImplGenerator(entityType));
    return generators;
  }
}
