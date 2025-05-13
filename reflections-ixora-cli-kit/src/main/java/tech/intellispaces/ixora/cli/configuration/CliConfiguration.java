package tech.intellispaces.ixora.cli.configuration;

import tech.intellispaces.ixora.cli.Consoles;
import tech.intellispaces.ixora.cli.MovableConsoleReflection;
import tech.intellispaces.reflections.framework.annotation.Configuration;
import tech.intellispaces.reflections.framework.annotation.Projection;

@Configuration
public class CliConfiguration {

  /**
   * Projection to module console.
   */
  @Projection
  public MovableConsoleReflection console() {
    return Consoles.dummy(System.out);
  }
}
