package tech.intellispaces.ixora.jetty;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tech.intellispaces.ixora.http.AbstractInboundHttpPortTest;
import tech.intellispaces.ixora.http.HttpPortExchangeChannel;
import tech.intellispaces.ixora.http.MovableInboundHttpPortHandle;

/**
 * Tests for {@link JettyServerPortHandleImpl} class.
 */
public class JettyServerPortHandleImplTest extends AbstractInboundHttpPortTest {

  @BeforeEach
  public void init() {
    super.init();
  }

  @AfterEach
  public void deinit() {
    super.deinit();
  }

  @Override
  public MovableInboundHttpPortHandle getOperativePort(
      int portNumber, Class<? extends HttpPortExchangeChannel> exchangeChannel
  ) {
    return JettyServerPorts.get(portNumber, exchangeChannel).asInboundHttpPort();
  }

  @Test
  public void testHello() throws Exception {
    super.testHello();
  }
}
