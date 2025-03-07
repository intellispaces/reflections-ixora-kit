package tech.intellispaces.ixora.http;

import com.sun.net.httpserver.HttpServer;
import org.assertj.core.api.Fail;
import tech.intellispaces.commons.collection.ArraysFunctions;
import tech.intellispaces.ixora.internet.uri.Uris;
import tech.intellispaces.jaquarius.object.reference.ObjectHandleFunctions;
import tech.intellispaces.jaquarius.object.reference.ObjectHandles;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link OutboundHttpPort} implementations.
 */
public abstract class OutboundHttpPortTest {
  private static final String TEST_ADDRESS = "http://localhost";
  private static final String HELLO_ENDPOINT = "/hello";
  private static final String HELLO_RESPONSE = "Hello!";

  protected abstract MovableOutboundHttpPort getPort();

  public void testHello() {
    HttpServer server = null;
    HttpResponse response = null;
    try {
      // Given
      server = getServer();
      server.start();

      HttpMethod methodHandle = mock(HttpMethod.class);
      when(methodHandle.isGetMethod()).thenReturn(true);

      HttpRequest requestHandle = mock(HttpRequest.class);
      when(requestHandle.method()).thenReturn(methodHandle);
      when(requestHandle.requestURI()).thenReturn(Uris.get(TEST_ADDRESS + HELLO_ENDPOINT));

      MovableOutboundHttpPort port = getPort();

      // When
      response = port.exchange(requestHandle);

      // Then
      HttpStatus status = response.status();
      assertThat(status.isOkStatus()).isTrue();

      byte[] body = ArraysFunctions.toByteArray(response.bodyStream().readAll().nativeList());
      assertThat(new String(body, StandardCharsets.UTF_8)).isEqualTo(HELLO_RESPONSE);
    } catch (Exception e) {
      Fail.fail("Unexpected exception", e);
    } finally {
      ObjectHandleFunctions.releaseSilently(ObjectHandles.handle(response));
      if (server != null) {
        server.stop(0);
      }
    }
  }

  private HttpServer getServer() throws IOException {
    HttpServer httpServer = HttpServer.create(new InetSocketAddress(80), 0);
    httpServer.createContext(HELLO_ENDPOINT, exchange -> {
      byte[] res = HELLO_RESPONSE.getBytes();
      exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, res.length);
      exchange.getResponseBody().write(res);
      exchange.close();
    });
    return httpServer;
  }
}
