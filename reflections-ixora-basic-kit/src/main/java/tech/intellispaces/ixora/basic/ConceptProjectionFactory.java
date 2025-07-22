package tech.intellispaces.ixora.basic;

import tech.intellispaces.core.ReflectionDomain;
import tech.intellispaces.ixora.basic.FocusedConceptProjection;
import tech.intellispaces.ixora.basic.ConceptProjectionAssistantCustomizer;
import tech.intellispaces.reflections.framework.annotation.Factory;

@Factory
public class ConceptProjectionFactory implements ConceptProjectionAssistantCustomizer {

  @Override
  public ConceptProjection focused(Object source, ReflectionDomain targetDomain, Object target) {
    return new FocusedConceptProjectionReflectionWrapper(source, targetDomain, target);
  }

  @Override
  public ConceptProjection fuzzy(Object source, ReflectionDomain targetDomain, Object collectiveTarget) {
    return new FuzzyConceptProjectionReflectionWrapper(source, targetDomain, collectiveTarget);
  }
}
