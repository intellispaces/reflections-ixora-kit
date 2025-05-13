package tech.intellispaces.ixora.cli;

import tech.intellispaces.reflections.framework.annotation.Factory;

import java.io.PrintStream;

@Factory
public class ConsoleFactory {

  public MovableConsoleReflection dummy(PrintStream ps) {
    return new PrintStreamConsoleReflectionWrapper(ps).asConsole();
  }
}
