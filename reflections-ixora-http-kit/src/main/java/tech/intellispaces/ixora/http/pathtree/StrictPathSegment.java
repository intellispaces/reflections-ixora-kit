package tech.intellispaces.ixora.http.pathtree;

public class StrictPathSegment extends PathSegment {
  private final String value;

  public StrictPathSegment(int index, String value) {
    this.index = index;
    this.value = value;
  }

  @Override
  public PathSegmentType type() {
    return PathSegmentType.Strict;
  }

  @Override
  public String value() {
    return value;
  }

  @Override
  public boolean conform(String segment) {
    return this.value.equals(segment);
  }
}
