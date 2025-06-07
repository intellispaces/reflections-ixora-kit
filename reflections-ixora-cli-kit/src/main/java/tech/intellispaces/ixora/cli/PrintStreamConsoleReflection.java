package tech.intellispaces.ixora.cli;

import java.io.PrintStream;
import java.util.Objects;

import tech.intellispaces.reflections.framework.annotation.Mover;
import tech.intellispaces.reflections.framework.annotation.Reflection;

@Reflection(domainClass = DummyConsoleDomain.class)
public abstract class PrintStreamConsoleReflection implements MovableDummyConsole {
  private final PrintStream ps;

  public PrintStreamConsoleReflection(PrintStream ps) {
    this.ps = ps;
  }

  public PrintStream getPrintStream() {
    return ps;
  }

  @Mover
  @Override
  public MovableDummyConsole print(String string) {
    Objects.requireNonNull(string);
    ps.print(string);
    return this;
  }

  @Mover
  @Override
  public MovableDummyConsole print(int number) {
    ps.print(number);
    return this;
  }

  @Mover
  @Override
  public MovableDummyConsole println(String string) {
    Objects.requireNonNull(string);
    ps.println(string);
    return this;
  }

  @Mover
  @Override
  public MovableDummyConsole println(int number) {
    ps.println(number);
    return this;
  }
}
