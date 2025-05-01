package tech.intellispaces.ixora.data.association;

import tech.intellispaces.jaquarius.annotation.Channel;
import tech.intellispaces.jaquarius.annotation.Dataset;
import tech.intellispaces.jaquarius.annotation.Domain;

@Dataset
@Domain("b833c1fc-e71e-43fc-b996-f8869642c807")
public interface NestedDataDomain {

  @Channel("8119ce95-9bc3-4825-858b-babdab0014ed")
  String stringValue();

  @Channel("8a17ef38-5f5b-4bd4-9c3a-a4c0c3fa37ef")
  NestedDataDomain nestedValue();
}
