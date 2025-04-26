package tech.intellispaces.ixora.cli;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tech.intellispaces.jaquarius.Jaquarius;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests fo {@link PrintStreamBasedConsole} class.
 */
public class PrintStreamBasedConsoleTest {

  @BeforeEach
  public void init() {
    Jaquarius.createModule().start();
  }

  @AfterEach
  public void destroy() {
    Jaquarius.releaseModule();
  }

  @Test
  public void testPrintStreamBasedConsole() {
    // Given
    var os = new ByteArrayOutputStream();
    var ps = new PrintStream(os, true, StandardCharsets.UTF_8);
    var console = new PrintStreamBasedConsoleWrapper(ps);

    // When
    console.println("abc");
    console.print("def");

    // Then
    String output = os.toString(StandardCharsets.UTF_8);
    assertThat(output).isEqualTo("abc" + System.lineSeparator() + "def");
  }
}
