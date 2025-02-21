package tech.intellispaces.ixora.http;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tech.intellispaces.ixora.internet.uri.JoinBasePathStringWithEndpointStringGuideImpl;
import tech.intellispaces.jaquarius.system.Modules;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link DedicatedHttpPortHandleImpl} class.
 */
public class DedicatedHttpPortHandleImplTest {

  @BeforeEach
  public void init() {
    Modules.load(JoinBasePathStringWithEndpointStringGuideImpl.class).start();
  }

  @AfterEach
  public void deinit() {
    Modules.unload();
  }

  @Test
  public void test() {
    // Given
    HttpMethodHandle httpGetMethod = HttpMethods.get();
    HttpMethodHandle httpPostMethod = HttpMethods.post();

    MovableHttpPortHandle underlyingPort = mock(MovableHttpPortHandle.class);

    HttpResponseHandle response1 = mock(HttpResponseHandle.class);
    when(underlyingPort.exchange(argThat(req -> req != null
        && req.method().name().equals(httpGetMethod.name())
        && req.requestURI().toString().equals("http:localhost:8080/api/test")))
    ).thenReturn(response1);

    HttpResponseHandle response2 = mock(HttpResponseHandle.class);
    when(underlyingPort.exchange(argThat(req -> req != null
        && req.method().name().equals(httpPostMethod.name())
        && req.requestURI().toString().equals("http:localhost:8080/api/test")))
    ).thenReturn(response2);

    String baseUrl = "http:localhost:8080/api";
    var dedicatedHttpPort = new DedicatedHttpPortHandleImplWrapper(baseUrl, underlyingPort);

    // When
    HttpResponseHandle actualResponse1 = dedicatedHttpPort.exchange("/test", httpGetMethod);
    HttpResponseHandle actualResponse2 = dedicatedHttpPort.exchange("test", httpPostMethod);

    // Then
    assertThat(actualResponse1).isSameAs(response1);
    assertThat(actualResponse2).isSameAs(response2);
  }
}
