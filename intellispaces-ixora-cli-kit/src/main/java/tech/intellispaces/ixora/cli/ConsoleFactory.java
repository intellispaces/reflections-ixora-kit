package tech.intellispaces.ixora.cli;

import java.io.PrintStream;

import tech.intellispaces.reflections.annotation.Factory;

@Factory
public class ConsoleFactory {

  public MovableConsoleHandle dummy(PrintStream ps) {
    return new PrintStreamBasedConsoleWrapper(ps).asConsole();
  }
}
