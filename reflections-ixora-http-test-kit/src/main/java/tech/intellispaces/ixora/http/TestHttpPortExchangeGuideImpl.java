package tech.intellispaces.ixora.http;

import tech.intellispaces.commons.text.StringFunctions;
import tech.intellispaces.ixora.data.stream.ByteInputStreams;
import tech.intellispaces.reflections.framework.annotation.Guide;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Guide
public class TestHttpPortExchangeGuideImpl implements TestHttpPortExchangeGuide {

  @Override
  public HttpResponseReflection exchange(TestPort port, HttpRequest request) {
    var status = mock(HttpStatusReflection.class);
    when(status.isOkStatus()).thenReturn(true);

    var res = mock(UnmovableHttpResponseReflection.class);
    when(res.status()).thenReturn(status);
    when(res.bodyStream()).thenReturn(ByteInputStreams.handleOf(StringFunctions.stringToInputStream("Hello")));
    return res;
  }
}
