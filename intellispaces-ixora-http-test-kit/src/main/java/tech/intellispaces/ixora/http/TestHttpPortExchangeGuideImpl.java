package tech.intellispaces.ixora.http;

import tech.intellispaces.commons.text.StringFunctions;
import tech.intellispaces.ixora.data.stream.ByteInputStreams;
import tech.intellispaces.jaquarius.annotation.Guide;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Guide
public class TestHttpPortExchangeGuideImpl implements TestHttpPortExchangeGuide {

  @Override
  public HttpResponseHandle exchange(TestPort port, HttpRequest request) {
    var status = mock(HttpStatusHandle.class);
    when(status.isOkStatus()).thenReturn(true);

    var res = mock(UnmovableHttpResponseHandle.class);
    when(res.status()).thenReturn(status);
    when(res.bodyStream()).thenReturn(ByteInputStreams.handleOf(StringFunctions.stringToInputStream("Hello")));
    return res;
  }
}
