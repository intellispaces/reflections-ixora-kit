package tech.intellispaces.ixora.http;

import tech.intellispaces.jaquarius.annotation.Channel;
import tech.intellispaces.jaquarius.annotation.Domain;
import tech.intellispaces.jaquarius.traverse.TraverseTypes;

@Domain("d0d4f50d-9042-4970-a523-7af9d6a1a8ff")
public interface TestPortDomain extends InboundHttpPortDomain {

  @Channel("03db10b5-a7fe-401c-bf5b-23a490319191")
  InboundHttpPortDomain asInboundHttpPort();

  @Channel(value = "2aaed635-8887-4f09-924f-7762e674d49d", allowedTraverse = TraverseTypes.Moving)
  TestPortDomain open();

  @Channel(value = "af7ad7ba-d029-4ff2-8b0e-cd34155d046a", allowedTraverse = TraverseTypes.Moving)
  TestPortDomain shut();

  @Override
  @Channel(
      value = "279f7e16-e59b-474d-96d0-8e53cbca9478",
      name = "TestHttpPortExchangeChannel",
      allowedTraverse = TraverseTypes.MappingOfMoving
  )
  HttpResponseDomain exchange(HttpRequestDomain request);
}
