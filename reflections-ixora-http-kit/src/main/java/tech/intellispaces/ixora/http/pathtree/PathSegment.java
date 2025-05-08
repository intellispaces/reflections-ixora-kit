package tech.intellispaces.ixora.http.pathtree;

import java.util.ArrayList;
import java.util.Collection;

public abstract class PathSegment {
  protected int index;
  private PathSegment previous;
  private Collection<PathSegment> next = new ArrayList<>();
  private FinalExecutor executorGet;

  public abstract PathSegmentType type();

  public abstract String value();

  public abstract boolean conform(String segment);

  public int index() {
    return index;
  }

  public PathSegment previous() {
    return previous;
  }

  public Collection<PathSegment> next() {
    return next;
  }

  public FinalExecutor executorGet() {
    return executorGet;
  }

  public void executorGet(FinalExecutor executor) {
    this.executorGet = executor;
  }
}
