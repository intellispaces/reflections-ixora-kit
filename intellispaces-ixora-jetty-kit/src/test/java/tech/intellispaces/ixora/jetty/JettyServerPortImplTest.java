package tech.intellispaces.ixora.jetty;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tech.intellispaces.ixora.http.AbstractInboundHttpPortTest;
import tech.intellispaces.ixora.http.MovableInboundHttpPort;
import tech.intellispaces.jaquarius.object.reference.MovableObjectHandle;

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
  public MovableInboundHttpPort createPort(
      int portNumber, MovableObjectHandle<?> overlyingHandle
  ) {
    return JettyServerPorts.create(portNumber, overlyingHandle);
  }

  @Test
  public void testHello() throws Exception {
    super.testHello();
  }
}
