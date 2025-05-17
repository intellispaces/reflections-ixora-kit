package tech.intellispaces.ixora.http;

import tech.intellispaces.reflections.framework.ReflectionsFramework;
import tech.intellispaces.reflections.framework.reflection.MovableReflection;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class AbstractInboundHttpPortTest {

  private static final int PORT_NUMBER = 8080;

  public abstract MovableInboundHttpPort createPort(
      int portNumber, MovableReflection<?> overlyingReflection
  );

  public void init() {
    ReflectionsFramework.loadModule(TestHttpPortExchangeGuideImpl.class).start();
  }

  public void deinit() {
    ReflectionsFramework.uploadModule();
  }

  public void testHello() throws Exception {
    MovableTestPortReflection testPort = TestPorts.create(overlyingReflection -> this.createPort(PORT_NUMBER, overlyingReflection));
    testPort.open();
    HttpResponse<String> res = callServer();
    String message = res.body();
    testPort.shut();
    assertThat(message).isEqualTo("Hello");
  }

  private HttpResponse<String> callServer() throws Exception {
    var req = java.net.http.HttpRequest.newBuilder()
        .uri(new URI("http://localhost:" + PORT_NUMBER))
        .GET()
        .build();
    return HttpClient.newHttpClient().send(req, HttpResponse.BodyHandlers.ofString());
  }
}
