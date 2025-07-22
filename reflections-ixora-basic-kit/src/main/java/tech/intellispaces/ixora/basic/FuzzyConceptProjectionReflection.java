package tech.intellispaces.ixora.basic;

import tech.intellispaces.core.ReflectionDomain;
import tech.intellispaces.ixora.basic.FuzzyConceptProjection;
import tech.intellispaces.ixora.basic.FuzzyConceptProjectionDomain;
import tech.intellispaces.reflections.framework.annotation.Mapper;
import tech.intellispaces.reflections.framework.annotation.Reflection;

@Reflection(domainClass = FuzzyConceptProjectionDomain.class)
public abstract class FuzzyConceptProjectionReflection implements FuzzyConceptProjection {
  private final Object sourceConcept;
  private final ReflectionDomain targetDomain;
  private final Object collectiveTargetConcept;

  public FuzzyConceptProjectionReflection(
      Object sourceConcept,
      ReflectionDomain targetDomain,
      Object collectiveTargetConcept
  ) {
    this.sourceConcept = sourceConcept;
    this.targetDomain = targetDomain;
    this.collectiveTargetConcept = collectiveTargetConcept;
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
  public Object collectiveTargetConcept() {
    return collectiveTargetConcept;
  }
}
