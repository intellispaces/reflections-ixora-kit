package tech.intellispaces.ixora.http;

import tech.intellispaces.jaquarius.annotation.Channel;

@Channel("279f7e16-e59b-474d-96d0-8e53cbca9478")
public interface TestPortExchangeChannel extends HttpPortExchangeChannel {

  HttpResponseDomain exchange(TestPortDomain source, HttpRequestDomain request);
}
