package tech.intellispaces.jaquarius.ixora.okhttp;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tech.intellispaces.jaquarius.ixora.http.MovableOutboundHttpPortHandle;
import tech.intellispaces.jaquarius.ixora.http.OutboundHttpPortTest;
import tech.intellispaces.jaquarius.system.Modules;

public class OkHttpPortHandleImplTest extends OutboundHttpPortTest {

  @BeforeEach
  public void init() {
    Modules.load().start();
  }

  @AfterEach
  public void deinit() {
    Modules.unload();
  }

  @Override
  protected MovableOutboundHttpPortHandle getPort() {
    return OkHttpPorts.get().asOutboundHttpPort();
  }

  @Test
  public void testHello() {
    super.testHello();
  }
}
