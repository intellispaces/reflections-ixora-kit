package tech.intellispaces.ixora.rdb.annotationprocessor;

import com.google.auto.service.AutoService;
import tech.intellispaces.commons.annotation.processor.ArtifactGenerator;
import tech.intellispaces.commons.annotation.processor.ArtifactGeneratorContext;
import tech.intellispaces.commons.annotation.processor.ArtifactProcessor;
import tech.intellispaces.commons.annotation.processor.ArtifactValidator;
import tech.intellispaces.commons.reflection.customtype.CustomType;
import tech.intellispaces.ixora.rdb.annotation.PersistedEntity;
import tech.intellispaces.jaquarius.annotationprocessor.AnnotationFunctions;
import tech.intellispaces.jaquarius.annotationprocessor.JaquariusArtifactProcessor;

import javax.annotation.processing.Processor;
import javax.lang.model.element.ElementKind;
import java.util.ArrayList;
import java.util.List;

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
