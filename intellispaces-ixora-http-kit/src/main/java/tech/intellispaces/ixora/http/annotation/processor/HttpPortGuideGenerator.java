package tech.intellispaces.ixora.http.annotation.processor;

import tech.intellispaces.commons.annotation.processor.ArtifactGeneratorContext;
import tech.intellispaces.commons.base.exception.NotImplementedExceptions;
import tech.intellispaces.commons.base.exception.UnexpectedExceptions;
import tech.intellispaces.commons.base.text.StringFunctions;
import tech.intellispaces.commons.java.reflection.customtype.CustomType;
import tech.intellispaces.commons.java.reflection.method.MethodParam;
import tech.intellispaces.commons.java.reflection.method.MethodSignatureDeclarations;
import tech.intellispaces.commons.java.reflection.method.MethodStatement;
import tech.intellispaces.commons.java.reflection.reference.TypeReference;
import tech.intellispaces.jaquarius.annotation.AutoGuide;
import tech.intellispaces.jaquarius.annotation.MapperOfMoving;
import tech.intellispaces.jaquarius.annotationprocessor.JaquariusArtifactGenerator;
import tech.intellispaces.ixora.data.collection.ListHandle;
import tech.intellispaces.ixora.http.HttpRequestHandle;
import tech.intellispaces.ixora.http.HttpResponseHandle;
import tech.intellispaces.ixora.http.annotation.QueryParam;
import tech.intellispaces.ixora.http.common.HttpNameConventionFunctions;
import tech.intellispaces.ixora.http.exception.HttpException;
import tech.intellispaces.ixora.internet.uri.GetUriQueryParamGuide;
import tech.intellispaces.jaquarius.object.reference.ObjectHandleFunctions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class HttpPortGuideGenerator extends JaquariusArtifactGenerator {
  private final CustomType portDomain;
  private final CustomType ontology;
  private final List<Map<String, Object>> guideMethods = new ArrayList<>();
  private final List<Map<String, Object>> ontologyMethods = new ArrayList<>();
  private boolean needUriToQueryParamGuide = false;

  public HttpPortGuideGenerator(CustomType portDomain, CustomType ontology) {
    super(ontology);
    this.portDomain = portDomain;
    this.ontology = ontology;
  }

  @Override
  public boolean isRelevant(ArtifactGeneratorContext context) {
    return true;
  }

  @Override
  public String generatedArtifactName() {
    return HttpNameConventionFunctions.getPortGuideCanonicalName(ontology);
  }

  @Override
  protected String templateName() {
    return "/http_port_guide.template";
  }

  @Override
  protected boolean analyzeSourceArtifact(ArtifactGeneratorContext context) {
    addImport(MapperOfMoving.class);

    analyzeMethods();

    addVariable("portSimpleName", addImportAndGetSimpleName(portDomain.canonicalName()));
    addVariable("ontologySimpleName", addImportAndGetSimpleName(ontology.canonicalName()));
    addVariable("guideMethods", guideMethods);
    addVariable("ontologyMethods", ontologyMethods);
    addVariable("needUriToQueryParamGuide", needUriToQueryParamGuide);
    return true;
  }

  private void analyzeMethods() {
    for (MethodStatement method : sourceArtifact().actualMethods()) {
      guideMethods.add(buildGuideMethod(method));
      ontologyMethods.add(buildMethod(method));
    }
  }

  private Map<String, Object> buildGuideMethod(MethodStatement method) {
    Map<String, Object> map = new HashMap<>();
    map.put("channelClass", addImportAndGetSimpleName(
        HttpNameConventionFunctions.getActualPortExchangeChannelCanonicalName(portDomain, ontology, method)));
    map.put("signature", buildGuideMethodSignature(method));
    map.put("body", buildGuideMethodBody(method));
    return map;
  }

  private String buildGuideMethodSignature(MethodStatement method) {
    var sb = new StringBuilder();
    sb.append(addImportAndGetSimpleName(HttpResponseHandle.class));
    sb.append(" ");
    sb.append(buildGuideMethodName(method));
    sb.append("(");
    sb.append(addImportAndGetSimpleName(ObjectHandleFunctions.getCommonObjectHandleTypename(portDomain)));
    sb.append(" port, ");
    sb.append(addImportAndGetSimpleName(HttpRequestHandle.class));
    sb.append(" request) throws ");
    sb.append(addImportAndGetSimpleName(HttpException.class));
    return sb.toString();
  }

  private String buildGuideMethodName(MethodStatement method) {
    var sb = new StringBuilder();
    sb.append("exchange");
    sb.append(StringFunctions.capitalizeFirstLetter(method.name()));
    for (MethodParam param : method.params()) {
      sb.append(StringFunctions.capitalizeFirstLetter(param.name()));
    }
    return sb.toString();
  }

  private String buildGuideMethodBody(MethodStatement method) {
    var sb = new StringBuilder();
    for (MethodParam param : method.params()) {
      appendMethodArgumentExtractorDeclaration(sb, param);
    }
    sb.append("return ");
    sb.append(method.name());
    sb.append("(");
    for (MethodParam param : method.params()) {
      appendMethodArgumentValueDeclaration(sb, param);
    }
    sb.append(");");
    return sb.toString();
  }

  private Map<String, Object> buildMethod(MethodStatement method) {
    String signature = MethodSignatureDeclarations.build(method)
        .paramDeclarationMapper(this::getGeneralObjectHandleDeclaration)
        .returnType(getGeneralObjectHandleDeclaration(method.returnType().orElseThrow()))
        .get(this::addImport, this::addImportAndGetSimpleName);

    Map<String, Object> map = new HashMap<>();
    map.put("signature", signature);
    return map;
  }

  private String getGeneralObjectHandleDeclaration(TypeReference domain) {
    return ObjectHandleFunctions.getGeneralObjectHandleDeclaration(domain, true, this::addImportAndGetSimpleName);
  }

  private void appendMethodArgumentExtractorDeclaration(StringBuilder sb, MethodParam param) {
    Function<MethodParam, String> mapper = getMethodArgumentExtractorDeclarationMapper(param);
    if (mapper == null) {
      throw UnexpectedExceptions.withMessage("Could not find method argument extractor declaration napper");
    }
    sb.append(mapper.apply(param));
  }

  private void appendMethodArgumentValueDeclaration(StringBuilder sb, MethodParam param) {
    Function<MethodParam, String> mapper = getMethodArgumentValueDeclarationMapper(param);
    if (mapper == null) {
      throw UnexpectedExceptions.withMessage("Could not find method argument declaration napper");
    }
    sb.append(mapper.apply(param));
  }

  protected Function<MethodParam, String> getMethodArgumentExtractorDeclarationMapper(MethodParam param) {
    if (param.type().isCustomTypeReference() && HttpRequestHandle.class.getCanonicalName().equals(
        param.type().asCustomTypeReferenceOrElseThrow().targetClass().getCanonicalName())
    ) {
      return this::buildMethodArgumentExtractorDeclarationEmpty;
    }
    if (param.hasAnnotation(QueryParam.class)) {
      return this::buildMethodQueryParamArgumentExtractorDeclaration;
    }
    return null;
  }

  protected Function<MethodParam, String> getMethodArgumentValueDeclarationMapper(MethodParam param) {
    if (param.type().isCustomTypeReference() && HttpRequestHandle.class.getCanonicalName().equals(
        param.type().asCustomTypeReferenceOrElseThrow().targetClass().getCanonicalName())
    ) {
      return this::buildMethodArgumentValueDeclarationDirect;
    }
    if (param.hasAnnotation(QueryParam.class)) {
      return this::buildMethodQueryParamArgumentValueDeclaration;
    }
    return null;
  }

  private String buildMethodQueryParamArgumentExtractorDeclaration(MethodParam param) {
    QueryParam queryParam = param.selectAnnotation(QueryParam.class).orElseThrow();
    String queryParamName = queryParam.value();
    String valueVariable = param.name() + "Value";
    String valuesVariable = param.name() + "Values";
    String castedValueVariable = param.name() + "Casted";
    includeUriToQueryParamGuide();

    StringBuilder sb = new StringBuilder();
    sb.append(addImportAndGetSimpleName(ListHandle.class));
    sb.append("<String> ");
    sb.append(valuesVariable);
    sb.append(" = getUriQueryParamGuide().map(request.requestURI(), \"");
    sb.append(queryParamName);
    sb.append("\");\n");
    sb.append("if (").append(valuesVariable).append(".size() == 0) {\n");
    sb.append("  throw ")
        .append(addImportAndGetSimpleName(UnexpectedExceptions.class))
        .append(".withMessage(\"Query parameter '")
        .append(queryParamName)
        .append("' was not found\");\n");
    sb.append("} else if (").append(valuesVariable).append(".size() > 1) {\n");
    sb.append("  throw ")
        .append(addImportAndGetSimpleName(UnexpectedExceptions.class))
        .append(".withMessage(\"Multiple query parameter '")
        .append(queryParamName)
        .append("' values found\");\n");
    sb.append("}\n");
    sb.append("String ");
    sb.append(valueVariable);
    sb.append(" = ");
    sb.append(valuesVariable);
    sb.append(".get(0);\n");
    sb.append(param.type().actualDeclaration(this::addImportAndGetSimpleName));
    sb.append(" ");
    sb.append(castedValueVariable);
    sb.append(" = ");
    if (String.class.getCanonicalName().equals(param.type().asCustomTypeReferenceOrElseThrow().targetType().canonicalName())) {
      sb.append(valueVariable);
      sb.append(";\n");
    } else {
      throw NotImplementedExceptions.withCode("14ruHA");
    }
    return sb.toString();
  }

  private String buildMethodQueryParamArgumentValueDeclaration(MethodParam param) {
    return param.name() + "Casted";
  }

  private void includeUriToQueryParamGuide() {
    needUriToQueryParamGuide = true;
    addImport(AutoGuide.class);
    addImport(GetUriQueryParamGuide.class);
  }

  private String buildMethodArgumentExtractorDeclarationEmpty(MethodParam param) {
    return "";
  }

  private String buildMethodArgumentValueDeclarationDirect(MethodParam param) {
    return param.name();
  }
}
