package tech.intellispaces.ixora.okhttp;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tech.intellispaces.ixora.http.MovableOutboundHttpPort;
import tech.intellispaces.ixora.http.OutboundHttpPortTest;
import tech.intellispaces.jaquarius.system.Modules;

public class OkHttpPortImplTest extends OutboundHttpPortTest {

  @BeforeEach
  public void init() {
    Modules.load().start();
  }

  @AfterEach
  public void deinit() {
    Modules.unload();
  }

  @Override
  protected MovableOutboundHttpPort getPort() {
    return OkHttpPorts.get().asOutboundHttpPort();
  }

  @Test
  public void testHello() {
    super.testHello();
  }
}
