package tech.intellispaces.ixora.http.annotationprocessor;

import tech.intellispaces.annotationprocessor.ArtifactGeneratorContext;
import tech.intellispaces.commons.abstraction.Reference;
import tech.intellispaces.ixora.http.HttpRequest;
import tech.intellispaces.ixora.http.HttpResponseReflection;
import tech.intellispaces.ixora.http.MovableInboundHttpPort;
import tech.intellispaces.ixora.http.common.HttpNameConventionFunctions;
import tech.intellispaces.ixora.http.engine.HttpPortEngines;
import tech.intellispaces.ixora.http.exception.HttpException;
import tech.intellispaces.javareflection.customtype.CustomType;
import tech.intellispaces.reflections.framework.annotation.MapperOfMoving;
import tech.intellispaces.reflections.framework.annotation.Mover;
import tech.intellispaces.reflections.framework.annotation.Reflection;
import tech.intellispaces.reflections.framework.annotationprocessor.ReflectionsArtifactGenerator;
import tech.intellispaces.reflections.framework.naming.NameConventionFunctions;
import tech.intellispaces.reflections.framework.reflection.DownwardObjectFactory;
import tech.intellispaces.reflections.framework.reflection.MovableReflection;

public class HttpPortReflectionGenerator extends ReflectionsArtifactGenerator {
  private String movableReflectionSimpleName;

  public HttpPortReflectionGenerator(CustomType entityType) {
    super(entityType);
  }

  @Override
  public boolean isRelevant(ArtifactGeneratorContext context) {
    return true;
  }

  @Override
  public String generatedArtifactName() {
    return HttpNameConventionFunctions.getPortReflectionCanonicalName(sourceArtifact());
  }

  @Override
  protected String templateName() {
    return "/http_port_reflection.template";
  }

  @Override
  protected boolean analyzeSourceArtifact(ArtifactGeneratorContext context) {
    addImport(Reflection.class);
    addImport(Mover.class);
    addImport(MapperOfMoving.class);
    addImport(HttpResponseReflection.class);
    addImport(HttpRequest.class);
    addImport(HttpException.class);
    addImport(MovableReflection.class);
    addImport(MovableInboundHttpPort.class);
    addImport(DownwardObjectFactory.class);
    addImport(Reference.class);
    addImport(HttpPortEngines.class);

    movableReflectionSimpleName = addImportAndGetSimpleName(
        NameConventionFunctions.getMovableReflectionTypeName(sourceArtifact().canonicalName(), true)
    );

    addVariable("movableReflectionSimpleName", movableReflectionSimpleName);
    return true;
  }
}
