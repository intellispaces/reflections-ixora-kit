package tech.intellispaces.ixora.http;

import tech.intellispaces.commons.base.text.StringFunctions;
import tech.intellispaces.jaquarius.annotation.Guide;
import tech.intellispaces.ixora.data.stream.DataStreams;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Guide
public class TestPortExchangeGuideImpl implements TestPortExchangeGuide {

  @Override
  public HttpResponseHandle exchange(TestPortHandle port, HttpRequestHandle request) {
    HttpStatusHandle statusHandle = mock(HttpStatusHandle.class);
    when(statusHandle.isOkStatus()).thenReturn(true);

    UnmovableHttpResponseHandle  responseHandle = mock(UnmovableHttpResponseHandle .class);
    when(responseHandle.status()).thenReturn(statusHandle);
    when(responseHandle.bodyStream()).thenReturn(DataStreams.get(StringFunctions.stringToInputStream("Hello")));

    return responseHandle;
  }
}
