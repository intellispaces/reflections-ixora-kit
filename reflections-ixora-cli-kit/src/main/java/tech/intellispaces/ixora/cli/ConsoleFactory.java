package tech.intellispaces.ixora.cli;

import java.io.PrintStream;

import tech.intellispaces.reflections.framework.annotation.Factory;

@Factory
public class ConsoleFactory {

  public MovableConsole dummy(PrintStream ps) {
    return new PrintStreamConsoleReflectionWrapper(ps);
  }
}
