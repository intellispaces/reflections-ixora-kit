package tech.intellispaces.ixora.cli;

import java.io.PrintStream;
import java.util.Objects;

import tech.intellispaces.reflections.framework.annotation.Mover;
import tech.intellispaces.reflections.framework.annotation.Reflection;

@Reflection(DummyConsoleDomain.class)
public abstract class PrintStreamConsoleReflectionImpl implements MovableDummyConsoleReflection {
  private final PrintStream ps;

  public PrintStreamConsoleReflectionImpl(PrintStream ps) {
    this.ps = ps;
  }

  public PrintStream getPrintStream() {
    return ps;
  }

  @Mover
  @Override
  public MovableDummyConsoleReflection print(String string) {
    Objects.requireNonNull(string);
    ps.print(string);
    return this;
  }

  @Mover
  @Override
  public MovableDummyConsoleReflection print(int number) {
    ps.print(number);
    return this;
  }

  @Mover
  @Override
  public MovableDummyConsoleReflection println(String string) {
    Objects.requireNonNull(string);
    ps.println(string);
    return this;
  }

  @Mover
  @Override
  public MovableDummyConsoleReflection println(int number) {
    ps.println(number);
    return this;
  }
}
