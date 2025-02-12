package tech.intellispaces.jaquarius.ixora.cli;

import tech.intellispaces.jaquarius.annotation.Mover;
import tech.intellispaces.jaquarius.annotation.ObjectHandle;

import java.io.PrintStream;
import java.util.Objects;

@ObjectHandle(ConsoleDomain.class)
public abstract class PrintStreamBasedConsole implements MovableConsoleHandle {
  private final PrintStream ps;

  public PrintStreamBasedConsole(PrintStream ps) {
    this.ps = ps;
  }

  public PrintStream getPrintStream() {
    return ps;
  }

  @Mover
  @Override
  public MovableConsoleHandle print(String string) {
    Objects.requireNonNull(string);
    ps.print(string);
    return this;
  }

  @Mover
  @Override
  public MovableConsoleHandle print(int number) {
    ps.print(number);
    return this;
  }

  @Mover
  @Override
  public MovableConsoleHandle println(String string) {
    Objects.requireNonNull(string);
    ps.println(string);
    return this;
  }

  @Mover
  @Override
  public MovableConsoleHandle println(int number) {
    ps.println(number);
    return this;
  }
}
