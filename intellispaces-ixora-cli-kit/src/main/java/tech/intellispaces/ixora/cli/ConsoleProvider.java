package tech.intellispaces.ixora.cli;

import tech.intellispaces.jaquarius.annotation.ObjectProvider;

import java.io.PrintStream;

@ObjectProvider
public class ConsoleProvider {

  public MovableConsoleHandle create(PrintStream ps) {
    return new PrintStreamBasedConsoleWrapper(ps);
  }
}
