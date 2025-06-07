package tech.intellispaces.ixora.http;

import tech.intellispaces.commons.text.StringFunctions;
import tech.intellispaces.ixora.data.stream.ByteInputStreams;
import tech.intellispaces.reflections.framework.annotation.Guide;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Guide
public class TestHttpPortExchangeGuideImpl implements TestHttpPortExchangeGuide {

  @Override
  public HttpResponse exchange(TestPort port, HttpRequest request) {
    var status = mock(HttpStatus.class);
    when(status.isOkStatus()).thenReturn(true);

    var res = mock(HttpResponse.class);
    when(res.status()).thenReturn(status);
    when(res.bodyStream()).thenReturn(ByteInputStreams.reflectionOf(StringFunctions.stringToInputStream("Hello")));
    return res;
  }
}
