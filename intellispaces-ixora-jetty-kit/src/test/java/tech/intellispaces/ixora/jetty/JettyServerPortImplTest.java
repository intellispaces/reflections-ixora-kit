package tech.intellispaces.ixora.jetty;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tech.intellispaces.ixora.http.AbstractInboundHttpPortTest;
import tech.intellispaces.ixora.http.HttpPortExchangeChannel;
import tech.intellispaces.ixora.http.MovableInboundHttpPort;

/**
 * Tests for {@link JettyServerPortImpl} class.
 */
public class JettyServerPortImplTest extends AbstractInboundHttpPortTest {

  @BeforeEach
  public void init() {
    super.init();
  }

  @AfterEach
  public void deinit() {
    super.deinit();
  }

  @Override
  public MovableInboundHttpPort getOperativePort(
      int portNumber, Class<? extends HttpPortExchangeChannel> exchangeChannel
  ) {
    return JettyServerPorts.get(portNumber, exchangeChannel).asInboundHttpPort();
  }

  @Test
  public void testHello() throws Exception {
    super.testHello();
  }
}
