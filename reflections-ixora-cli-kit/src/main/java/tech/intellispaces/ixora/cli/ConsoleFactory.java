package tech.intellispaces.ixora.cli;

import java.io.PrintStream;

import tech.intellispaces.reflections.framework.annotation.Factory;

@Factory
public class ConsoleFactory {

  public MovableConsoleReflection dummy(PrintStream ps) {
    return new PrintStreamConsoleReflectionImplWrapper(ps).asConsole();
  }
}
