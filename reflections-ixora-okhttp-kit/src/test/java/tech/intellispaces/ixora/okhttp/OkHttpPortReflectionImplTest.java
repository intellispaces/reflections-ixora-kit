package tech.intellispaces.ixora.okhttp;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tech.intellispaces.ixora.http.MovableOutboundHttpPortReflection;
import tech.intellispaces.ixora.http.OutboundHttpPortTest;
import tech.intellispaces.reflections.framework.ReflectionsFramework;

public class OkHttpPortReflectionImplTest extends OutboundHttpPortTest {

  @BeforeEach
  public void init() {
    ReflectionsFramework.loadModule().start();
  }

  @AfterEach
  public void deinit() {
    ReflectionsFramework.uploadModule();
  }

  @Override
  protected MovableOutboundHttpPortReflection getPort() {
    return OkHttpPorts.create().asOutboundHttpPort();
  }

  @Test
  public void testHello() {
    super.testHello();
  }
}
