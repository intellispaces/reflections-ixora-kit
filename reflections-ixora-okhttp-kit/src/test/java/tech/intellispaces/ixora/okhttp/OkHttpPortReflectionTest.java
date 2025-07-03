package tech.intellispaces.ixora.okhttp;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tech.intellispaces.ixora.http.MovableOutboundHttpPort;
import tech.intellispaces.ixora.http.OutboundHttpPortTest;
import tech.intellispaces.reflections.framework.ReflectionsFramework;
import tech.intellispaces.reflections.framework.system.ReflectionModule;

public class OkHttpPortReflectionTest extends OutboundHttpPortTest {
  private ReflectionModule module;

  @BeforeEach
  public void init() {
    module = ReflectionsFramework.loadModule().start();
  }

  @AfterEach
  public void deinit() {
    module.stop().upload();
  }

  @Override
  protected MovableOutboundHttpPort getPort() {
    return OkHttpPorts.create();
  }

  @Test
  public void testHello() {
    super.testHello();
  }
}
