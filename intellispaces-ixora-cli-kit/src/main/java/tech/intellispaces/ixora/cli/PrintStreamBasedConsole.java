package tech.intellispaces.ixora.cli;

import tech.intellispaces.jaquarius.annotation.Mover;
import tech.intellispaces.jaquarius.annotation.ObjectHandle;

import java.io.PrintStream;
import java.util.Objects;

@ObjectHandle(ConsoleDomain.class)
public abstract class PrintStreamBasedConsole implements MovableConsole {
  private final PrintStream ps;

  public PrintStreamBasedConsole(PrintStream ps) {
    this.ps = ps;
  }

  public PrintStream getPrintStream() {
    return ps;
  }

  @Mover
  @Override
  public MovableConsole print(String string) {
    Objects.requireNonNull(string);
    ps.print(string);
    return this;
  }

  @Mover
  @Override
  public MovableConsole print(int number) {
    ps.print(number);
    return this;
  }

  @Mover
  @Override
  public MovableConsole println(String string) {
    Objects.requireNonNull(string);
    ps.println(string);
    return this;
  }

  @Mover
  @Override
  public MovableConsole println(int number) {
    ps.println(number);
    return this;
  }
}
