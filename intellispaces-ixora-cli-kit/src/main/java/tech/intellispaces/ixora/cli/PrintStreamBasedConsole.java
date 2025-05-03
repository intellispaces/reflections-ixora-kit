package tech.intellispaces.ixora.cli;

import java.io.PrintStream;
import java.util.Objects;

import tech.intellispaces.reflections.annotation.Mover;
import tech.intellispaces.reflections.annotation.ObjectHandle;

@ObjectHandle(DummyConsoleDomain.class)
public abstract class PrintStreamBasedConsole implements MovableDummyConsoleHandle {
  private final PrintStream ps;

  public PrintStreamBasedConsole(PrintStream ps) {
    this.ps = ps;
  }

  public PrintStream getPrintStream() {
    return ps;
  }

  @Mover
  @Override
  public MovableDummyConsoleHandle print(String string) {
    Objects.requireNonNull(string);
    ps.print(string);
    return this;
  }

  @Mover
  @Override
  public MovableDummyConsoleHandle print(int number) {
    ps.print(number);
    return this;
  }

  @Mover
  @Override
  public MovableDummyConsoleHandle println(String string) {
    Objects.requireNonNull(string);
    ps.println(string);
    return this;
  }

  @Mover
  @Override
  public MovableDummyConsoleHandle println(int number) {
    ps.println(number);
    return this;
  }
}
