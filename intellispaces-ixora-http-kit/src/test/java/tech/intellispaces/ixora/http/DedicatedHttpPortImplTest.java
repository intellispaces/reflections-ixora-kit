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
 * Tests for {@link DedicatedHttpPortImpl} class.
 */
public class DedicatedHttpPortImplTest {

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
    HttpMethod httpGetMethod = HttpMethods.get();
    HttpMethod httpPostMethod = HttpMethods.post();

    MovableHttpPort underlyingPort = mock(MovableHttpPort.class);

    HttpResponse response1 = mock(HttpResponse.class);
    when(underlyingPort.exchange(argThat(req -> req != null
        && req.method().name().equals(httpGetMethod.name())
        && req.requestURI().toString().equals("http:localhost:8080/api/test")))
    ).thenReturn(response1);

    HttpResponse response2 = mock(HttpResponse.class);
    when(underlyingPort.exchange(argThat(req -> req != null
        && req.method().name().equals(httpPostMethod.name())
        && req.requestURI().toString().equals("http:localhost:8080/api/test")))
    ).thenReturn(response2);

    String baseUrl = "http:localhost:8080/api";
    var dedicatedHttpPort = new DedicatedHttpPortImplWrapper(baseUrl, underlyingPort);

    // When
    HttpResponse actualResponse1 = dedicatedHttpPort.exchange("/test", httpGetMethod);
    HttpResponse actualResponse2 = dedicatedHttpPort.exchange("test", httpPostMethod);

    // Then
    assertThat(actualResponse1).isSameAs(response1);
    assertThat(actualResponse2).isSameAs(response2);
  }
}
