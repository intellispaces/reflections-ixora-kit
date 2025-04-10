package tech.intellispaces.ixora.cli;

import tech.intellispaces.jaquarius.annotation.ObjectFactory;

import java.io.PrintStream;

@ObjectFactory
public class ConsoleProvider {

  public MovableConsoleHandle dummy(PrintStream ps) {
    return new PrintStreamBasedConsoleWrapper(ps).asConsole();
  }
}
