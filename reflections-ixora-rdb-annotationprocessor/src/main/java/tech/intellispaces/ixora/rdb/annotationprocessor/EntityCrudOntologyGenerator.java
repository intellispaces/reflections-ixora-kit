package tech.intellispaces.ixora.rdb.annotationprocessor;

import tech.intellispaces.annotationprocessor.ArtifactGeneratorContext;
import tech.intellispaces.core.id.IdentifierFunctions;
import tech.intellispaces.ixora.rdb.transaction.TransactionDomain;
import tech.intellispaces.jstatements.customtype.CustomType;
import tech.intellispaces.jstatements.method.MethodStatement;
import tech.intellispaces.reflections.annotationprocessor.ReflectionsArtifactGenerator;
import tech.intellispaces.reflections.framework.annotation.Channel;
import tech.intellispaces.reflections.framework.annotation.Ontology;
import tech.intellispaces.reflections.framework.id.RepetableUuidIdentifierGenerator;
import tech.intellispaces.reflections.framework.space.domain.DomainFunctions;

import java.util.Optional;
import java.util.UUID;

public class EntityCrudOntologyGenerator extends ReflectionsArtifactGenerator {
  private String transactionToEntityByIdentifierCid;
  private String transactionToNewEntityCid;

  private boolean entityHasIdentifier;
  private String identifierType;
  private String transactionToEntityByIdentifierChannelSimpleName;
  private String transactionToNewEntityChannelSimpleName;

  public EntityCrudOntologyGenerator(CustomType entityType) {
    super(entityType);
  }

  @Override
  public boolean isRelevant(ArtifactGeneratorContext context) {
    return true;
  }

  @Override
  public String generatedArtifactName() {
    return EntityAnnotationFunctions.getCrudOntologyCanonicalName(sourceArtifact());
  }

  @Override
  protected String templateName() {
    return "/entity_crud_ontology.template";
  }

  @Override
  protected boolean analyzeSourceArtifact(ArtifactGeneratorContext context) {
    addImport(Ontology.class);
    addImport(Channel.class);
    addImport(TransactionDomain.class);

    defineIdentifiers();
    analyzeEntityIdentifier();

    addVariable("entityHasIdentifier", entityHasIdentifier);
    addVariable("identifierType", identifierType);
    addVariable("transactionToEntityByIdentifierChannelSimpleName", transactionToEntityByIdentifierChannelSimpleName);
    addVariable("transactionToNewEntityChannelSimpleName", transactionToNewEntityChannelSimpleName);

    addVariable("transactionToEntityByIdentifierCid", transactionToEntityByIdentifierCid);
    addVariable("transactionToNewEntityCid", transactionToNewEntityCid);
    return true;
  }

  private void defineIdentifiers() {
    String did = DomainFunctions.getDomainId(sourceArtifact());
    var identifierGenerator = new RepetableUuidIdentifierGenerator(UUID.fromString(did));
    transactionToEntityByIdentifierCid = IdentifierFunctions.convertToHexString(identifierGenerator.next());
    transactionToNewEntityCid = IdentifierFunctions.convertToHexString(identifierGenerator.next());
  }

  private void analyzeEntityIdentifier() {
    Optional<MethodStatement> identifierMethod = EntityAnnotationFunctions.findIdentifierMethod(sourceArtifact());
    if (identifierMethod.isEmpty()) {
      entityHasIdentifier = false;
      return;
    }
    entityHasIdentifier = true;

    transactionToEntityByIdentifierChannelSimpleName = EntityAnnotationFunctions.getTransactionToEntityByIdentifierChannelSimpleName(
        sourceArtifact()
    );
    transactionToNewEntityChannelSimpleName = EntityAnnotationFunctions.getTransactionToNewEntityChannelSimpleName(
        sourceArtifact()
    );

    identifierType = addImportAndGetSimpleName(
      EntityAnnotationFunctions.getIdentifierType(sourceArtifact(), identifierMethod.orElseThrow())
    );
  }
}
