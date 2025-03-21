package tech.intellispaces.ixora.cli;

import java.io.PrintStream;

public interface ConsolesCustomizer {

  static MovableConsole get(PrintStream ps) {
    return new PrintStreamBasedConsoleWrapper(ps);
  }
}
