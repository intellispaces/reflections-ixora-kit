package tech.intellispaces.jaquarius.ixora.internet.uri;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link GetUriQueryParamGuide} implementations.
 */
public abstract class AbstractGetUriQueryParamGuideTest {

  abstract GetUriQueryParamGuide getGuide();

  abstract UriHandle getUri(String string);

  public void testUrlToQueryParamValues() {
    GetUriQueryParamGuide guide = getGuide();

    assertThat(guide.getUriQueryParam(null, null)).isNull();
    assertThat(guide.getUriQueryParam(getUri("http://localhost:8080/test?param1=value1"), null)).isNull();
    assertThat(guide.getUriQueryParam(null, "param1")).isNull();

    assertThat(guide.getUriQueryParam(getUri("http://localhost:8080/test?param2=value2"), "param1").nativeList()).isEmpty();
    assertThat(guide.getUriQueryParam(getUri("http://localhost:8080/test?param1=value1"), "param1").nativeList())
        .contains("value1");
    assertThat(guide.getUriQueryParam(getUri("http://localhost:8080/test?param1=value1&param2=value2"), "param1").nativeList())
        .contains("value1");
    assertThat(guide.getUriQueryParam(getUri("http://localhost:8080/test?param1=value1&param1=value2"), "param1").nativeList())
        .contains("value1", "value2");

    assertThat(guide.getUriQueryParam(getUri("/test?param2=value2"), "param1").nativeList()).isEmpty();
    assertThat(guide.getUriQueryParam(getUri("/test?param1=value1"), "param1").nativeList())
        .contains("value1");
    assertThat(guide.getUriQueryParam(getUri("/test?param1=value1&param2=value2"), "param1").nativeList())
        .contains("value1");
    assertThat(guide.getUriQueryParam(getUri("/test?param1=value1&param1=value2"), "param1").nativeList())
        .contains("value1", "value2");
  }
}
