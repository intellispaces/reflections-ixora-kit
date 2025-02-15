package tech.intellispaces.jaquarius.ixora.data.dictionary;

import tech.intellispaces.jaquarius.annotation.Channel;
import tech.intellispaces.jaquarius.annotation.Dataset;
import tech.intellispaces.jaquarius.annotation.Domain;

@Dataset
@Domain("0bb8a186-8692-45b2-8018-5d56beda0239")
public interface SimpleDataDomain {

  @Channel("6d8468c0-2cf2-4a95-9535-c731a40d17e9")
  Integer intValue();

  @Channel("8f1359e9-0adc-4881-8480-933110ae3912")
  Double doubleValue();

  @Channel("d083ddee-a5af-46fe-8f56-c63e20eca986")
  String stringValue();
}
