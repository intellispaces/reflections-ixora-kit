package tech.intellispaces.jaquarius.ixora.cli;

import java.io.PrintStream;

public interface Consoles {

  static MovableConsoleHandle get(PrintStream ps) {
    return new PrintStreamBasedConsoleWrapper(ps);
  }
}
