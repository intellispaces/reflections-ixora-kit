package tech.intellispaces.ixora.http;

import com.sun.net.httpserver.HttpServer;
import org.assertj.core.api.Fail;
import tech.intellispaces.commons.collection.ArraysFunctions;
import tech.intellispaces.ixora.internet.uri.Uris;
import tech.intellispaces.reflections.framework.reflection.Reflections;
import tech.intellispaces.reflections.framework.reflection.ReflectionFunctions;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static tech.intellispaces.commons.collection.CollectionFunctions.toList;

/**
 * Tests for {@link OutboundHttpPort} implementations.
 */
public abstract class OutboundHttpPortTest {
  private static final String TEST_ADDRESS = "http://localhost";
  private static final String HELLO_ENDPOINT = "/hello";
  private static final String HELLO_RESPONSE = "Hello!";

  protected abstract MovableOutboundHttpPortReflection getPort();

  public void testHello() {
    HttpServer server = null;
    HttpResponseReflection response = null;
    try {
      // Given
      server = getServer();
      server.start();

      HttpMethod method = mock(HttpMethod.class);
      when(method.isGetMethod()).thenReturn(true);

      HttpRequest request = mock(HttpRequest.class);
      when(request.method()).thenReturn(method);
      when(request.requestURI()).thenReturn(Uris.create(TEST_ADDRESS + HELLO_ENDPOINT));

      MovableOutboundHttpPortReflection port = getPort();

      // When
      response = port.exchange(request);

      // Then
      HttpStatus status = response.status();
      assertThat(status.isOkStatus()).isTrue();

      byte[] body = ArraysFunctions.toByteArray(toList(response.bodyStream().readAll().iterator()));
      assertThat(new String(body, StandardCharsets.UTF_8)).isEqualTo(HELLO_RESPONSE);
    } catch (Exception e) {
      Fail.fail("Unexpected exception", e);
    } finally {
      ReflectionFunctions.unbindSilently(Reflections.reflection(response));
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
