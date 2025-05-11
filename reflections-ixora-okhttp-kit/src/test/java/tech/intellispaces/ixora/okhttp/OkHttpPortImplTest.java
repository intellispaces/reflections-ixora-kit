package tech.intellispaces.ixora.okhttp;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tech.intellispaces.ixora.http.MovableOutboundHttpPortHandle;
import tech.intellispaces.ixora.http.OutboundHttpPortTest;
import tech.intellispaces.reflections.framework.Jaquarius;

public class OkHttpPortImplTest extends OutboundHttpPortTest {

  @BeforeEach
  public void init() {
    Jaquarius.createModule().start();
  }

  @AfterEach
  public void deinit() {
    Jaquarius.releaseModule();
  }

  @Override
  protected MovableOutboundHttpPortHandle getPort() {
    return OkHttpPorts.create().asOutboundHttpPort();
  }

  @Test
  public void testHello() {
    super.testHello();
  }
}
