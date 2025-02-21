package tech.intellispaces.ixora.cli;

import tech.intellispaces.jaquarius.annotation.Configuration;
import tech.intellispaces.jaquarius.annotation.Projection;

@Configuration
public class CliConfiguration {

  /**
   * Projection to module system console.
   */
  @Projection
  public MovableConsoleHandle console() {
    return Consoles.get(System.out);
  }
}
