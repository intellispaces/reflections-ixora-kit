package tech.intellispaces.ixora.okhttp;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tech.intellispaces.core.Module;
import tech.intellispaces.ixora.http.MovableOutboundHttpPortReflection;
import tech.intellispaces.ixora.http.OutboundHttpPortTest;
import tech.intellispaces.reflections.framework.ReflectionsFramework;

public class OkHttpPortReflectionImplTest extends OutboundHttpPortTest {
  private Module module;

  @BeforeEach
  public void init() {
    module = ReflectionsFramework.loadModule().start();
  }

  @AfterEach
  public void deinit() {
    module.stop().upload();
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
