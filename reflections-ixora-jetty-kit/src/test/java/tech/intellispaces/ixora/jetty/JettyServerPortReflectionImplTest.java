package tech.intellispaces.ixora.jetty;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tech.intellispaces.ixora.http.AbstractInboundHttpPortTest;
import tech.intellispaces.ixora.http.MovableInboundHttpPort;
import tech.intellispaces.reflections.framework.reflection.MovableReflection;

/**
 * Tests for {@link JettyServerPortReflectionImpl} class.
 */
public class JettyServerPortReflectionImplTest extends AbstractInboundHttpPortTest {

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
      int portNumber, MovableReflection<?> overlyingReflection
  ) {
    return JettyServerPorts.create(portNumber, overlyingReflection);
  }

  @Test
  public void testHello() throws Exception {
    super.testHello();
  }
}
