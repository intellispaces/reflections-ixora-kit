package tech.intellispaces.ixora.data.json;

import tech.intellispaces.ixora.data.DatasetDomain;
import tech.intellispaces.reflections.framework.annotation.Channel;
import tech.intellispaces.reflections.framework.annotation.Dataset;
import tech.intellispaces.reflections.framework.annotation.Domain;

@Dataset
@Domain("66b466af-ebb6-4fa5-8dfa-99c2c1338323")
public interface SimpleDatasetDomain extends DatasetDomain {

  @Channel("e2757ff6-ebe7-48fd-bc86-409a2d5ea5e5")
  String stringAttribute();

//  @Channel("5ad2be58-1339-40e0-8932-ce6a74fb218d")
//  int intAttribute();

//  @Channel("64111c93-9890-40e5-a566-22ff2f2c69b2")
//  double doubleAttribute();
}
