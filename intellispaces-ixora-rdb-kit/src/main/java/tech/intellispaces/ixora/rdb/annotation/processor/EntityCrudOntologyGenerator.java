package tech.intellispaces.ixora.rdb.annotation.processor;

import tech.intellispaces.commons.annotation.processor.ArtifactGeneratorContext;
import tech.intellispaces.commons.reflection.customtype.CustomType;
import tech.intellispaces.commons.reflection.method.MethodStatement;
import tech.intellispaces.ixora.rdb.transaction.TransactionDomain;
import tech.intellispaces.jaquarius.annotation.Channel;
import tech.intellispaces.jaquarius.annotation.Ontology;
import tech.intellispaces.jaquarius.annotationprocessor.JaquariusArtifactGenerator;
import tech.intellispaces.jaquarius.id.RepetableUuidIdentifierGenerator;
import tech.intellispaces.jaquarius.space.domain.DomainFunctions;

import java.util.Optional;
import java.util.UUID;

public class EntityCrudOntologyGenerator extends JaquariusArtifactGenerator {
  private String transactionToEntityByIdentifierCid;

  private boolean entityHasIdentifier;
  private String identifierType;
  private String transactionToEntityByIdentifierChannelSimpleName;

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
    addVariable("transactionToEntityByIdentifierCid", transactionToEntityByIdentifierCid);
    return true;
  }

  private void defineIdentifiers() {
    String did = DomainFunctions.getDomainId(sourceArtifact());
    var identifierGenerator = new RepetableUuidIdentifierGenerator(UUID.fromString(did));
    transactionToEntityByIdentifierCid = identifierGenerator.next();
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
    identifierType = addImportAndGetSimpleName(
      EntityAnnotationFunctions.getIdentifierType(sourceArtifact(), identifierMethod.orElseThrow())
    );
  }
}
