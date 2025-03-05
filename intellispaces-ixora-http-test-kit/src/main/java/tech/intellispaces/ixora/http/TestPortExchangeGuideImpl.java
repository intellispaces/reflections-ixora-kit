package tech.intellispaces.ixora.http;

import tech.intellispaces.commons.base.text.StringFunctions;
import tech.intellispaces.ixora.data.stream.DataStreams;
import tech.intellispaces.jaquarius.annotation.Guide;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Guide
public class TestPortExchangeGuideImpl implements TestPortExchangeGuide {

  @Override
  public HttpResponse exchange(TestPort port, HttpRequest request) {
    HttpStatus statusHandle = mock(HttpStatus.class);
    when(statusHandle.isOkStatus()).thenReturn(true);

    UnmovableHttpResponse  responseHandle = mock(UnmovableHttpResponse .class);
    when(responseHandle.status()).thenReturn(statusHandle);
    when(responseHandle.bodyStream()).thenReturn(DataStreams.get(StringFunctions.stringToInputStream("Hello")));

    return responseHandle;
  }
}
