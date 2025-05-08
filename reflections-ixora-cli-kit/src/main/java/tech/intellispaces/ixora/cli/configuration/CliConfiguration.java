package tech.intellispaces.ixora.cli.configuration;

import tech.intellispaces.ixora.cli.Consoles;
import tech.intellispaces.ixora.cli.MovableConsoleHandle;
import tech.intellispaces.reflections.annotation.Configuration;
import tech.intellispaces.reflections.annotation.Projection;

@Configuration
public class CliConfiguration {

  /**
   * Projection to module console.
   */
  @Projection
  public MovableConsoleHandle console() {
    return Consoles.dummy(System.out);
  }
}
