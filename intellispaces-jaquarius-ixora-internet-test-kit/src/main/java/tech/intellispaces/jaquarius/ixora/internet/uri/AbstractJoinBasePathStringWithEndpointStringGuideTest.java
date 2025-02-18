package tech.intellispaces.jaquarius.ixora.internet.uri;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Abstract test for {@link JoinBasePathStringWithEndpointStringGuide} implementations.
 */
public abstract class AbstractJoinBasePathStringWithEndpointStringGuideTest {

  abstract JoinBasePathStringWithEndpointStringGuide getGuide();

  public void testJoinUrl() {
    JoinBasePathStringWithEndpointStringGuide guide = getGuide();

    assertThat(guide.joinBasePathStringWithEndpointString(null, null)).isNull();
    assertThat(guide.joinBasePathStringWithEndpointString("http://localhost", null)).isEqualTo("http://localhost");
    assertThat(guide.joinBasePathStringWithEndpointString(null, "test")).isEqualTo("test");

    assertThat(guide.joinBasePathStringWithEndpointString("http://localhost", "test")).isEqualTo("http://localhost/test");
    assertThat(guide.joinBasePathStringWithEndpointString("http://localhost", "/test")).isEqualTo("http://localhost/test");
  }
}
