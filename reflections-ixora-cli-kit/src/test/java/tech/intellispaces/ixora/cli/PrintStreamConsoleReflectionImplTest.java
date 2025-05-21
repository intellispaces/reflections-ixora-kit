package tech.intellispaces.ixora.cli;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tech.intellispaces.reflections.framework.ReflectionsFramework;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests fo {@link PrintStreamConsoleReflectionImpl} class.
 */
public class PrintStreamConsoleReflectionImplTest {
  private tech.intellispaces.core.Module module;

  @BeforeEach
  public void init() {
    module = ReflectionsFramework.loadModule().start();
  }

  @AfterEach
  public void destroy() {
    module.stop().upload();
  }

  @Test
  public void testPrintStreamBasedConsole() {
    // Given
    var os = new ByteArrayOutputStream();
    var ps = new PrintStream(os, true, StandardCharsets.UTF_8);
    var console = new PrintStreamConsoleReflectionImplWrapper(ps);

    // When
    console.println("abc");
    console.print("def");

    // Then
    String output = os.toString(StandardCharsets.UTF_8);
    assertThat(output).isEqualTo("abc" + System.lineSeparator() + "def");
  }
}
