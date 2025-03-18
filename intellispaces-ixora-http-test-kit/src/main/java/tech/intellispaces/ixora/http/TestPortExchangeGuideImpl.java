package tech.intellispaces.ixora.http;

import tech.intellispaces.commons.text.StringFunctions;
import tech.intellispaces.ixora.data.stream.DataStreams;
import tech.intellispaces.jaquarius.annotation.Guide;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Guide
public class TestPortExchangeGuideImpl implements TestPortExchangeGuide {

  @Override
  public HttpResponseHandle exchange(TestPort port, HttpRequest request) {
    var status = mock(HttpStatusHandle.class);
    when(status.isOkStatus()).thenReturn(true);

    var responseHandle = mock(UnmovableHttpResponseHandle.class);
    when(responseHandle.status()).thenReturn(status);
    when(responseHandle.bodyStream()).thenReturn(DataStreams.get(StringFunctions.stringToInputStream("Hello")));
    return responseHandle;
  }
}
