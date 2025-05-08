package tech.intellispaces.ixora.http.pathtree;

import java.util.regex.Pattern;

public class RegexPathSegment extends PathSegment {
  private final String value;
  private Pattern pattern;

  public RegexPathSegment(int index, String value) {
    this.index = index;
    this.value = value;
    this.pattern = Pattern.compile(value);
  }

  @Override
  public PathSegmentType type() {
    return PathSegmentType.Regex;
  }

  @Override
  public String value() {
    return value;
  }

  @Override
  public boolean conform(String segment) {
    return pattern.matcher(segment).matches();
  }
}
