package tech.intellispaces.ixora.http.engine;

public final class HttpPortEngines {
  private static final HttpPortEngine ENGINE = new DefaultHttpPortEngine();

  public static HttpPortEngine get() {
    return ENGINE;
  }

  private HttpPortEngines() {}
}
