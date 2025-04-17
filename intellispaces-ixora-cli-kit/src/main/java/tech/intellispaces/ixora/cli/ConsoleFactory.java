package tech.intellispaces.ixora.cli;

import tech.intellispaces.jaquarius.annotation.Factory;

import java.io.PrintStream;

@Factory
public class ConsoleFactory {

  public MovableConsoleHandle dummy(PrintStream ps) {
    return new PrintStreamBasedConsoleWrapper(ps).asConsole();
  }
}
