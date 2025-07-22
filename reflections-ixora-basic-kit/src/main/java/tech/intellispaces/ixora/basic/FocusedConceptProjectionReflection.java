package tech.intellispaces.ixora.basic;

import tech.intellispaces.core.ReflectionDomain;
import tech.intellispaces.ixora.basic.FocusedConceptProjection;
import tech.intellispaces.ixora.basic.FocusedConceptProjectionDomain;
import tech.intellispaces.reflections.framework.annotation.Mapper;
import tech.intellispaces.reflections.framework.annotation.Reflection;

@Reflection(domainClass = FocusedConceptProjectionDomain.class)
public abstract class FocusedConceptProjectionReflection implements FocusedConceptProjection {
  private final Object sourceConcept;
  private final ReflectionDomain targetDomain;
  private final Object targetConcept;

  public FocusedConceptProjectionReflection(
      Object sourceConcept,
      ReflectionDomain targetDomain,
      Object targetConcept
  ) {
    this.sourceConcept = sourceConcept;
    this.targetDomain = targetDomain;
    this.targetConcept = targetConcept;
  }

  @Mapper
  @Override
  public Object sourceConcept() {
    return sourceConcept;
  }

  @Mapper
  @Override
  public Object targetConceptDomain() {
    return targetDomain;
  }

  @Mapper
  @Override
  public Object targetConcept() {
    return targetConcept;
  }
}
