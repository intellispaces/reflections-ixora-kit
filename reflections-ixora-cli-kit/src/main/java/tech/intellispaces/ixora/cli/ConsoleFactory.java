package tech.intellispaces.ixora.cli;

import tech.intellispaces.reflections.framework.annotation.Factory;

import java.io.PrintStream;

@Factory
public class ConsoleFactory {

  public MovableConsoleHandle dummy(PrintStream ps) {
    return new PrintStreamBasedConsoleWrapper(ps).asConsole();
  }
}
