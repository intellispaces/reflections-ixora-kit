package tech.intellispaces.ixora.internet.uri;

import static org.assertj.core.api.Assertions.assertThat;
import static tech.intellispaces.commons.collection.CollectionFunctions.toList;

/**
 * Tests for {@link GetUriQueryParamGuide} implementations.
 */
public abstract class AbstractGetUriQueryParamGuideTest {

  abstract GetUriQueryParamGuide getGuide();

  abstract Uri getUri(String string);

  public void testUrlToQueryParamValues() {
    GetUriQueryParamGuide guide = getGuide();

    assertThat(guide.getUriQueryParam(null, null)).isNull();
    assertThat(guide.getUriQueryParam(getUri("http://localhost:8080/test?param1=value1"), null)).isNull();
    assertThat(guide.getUriQueryParam(null, "param1")).isNull();

    assertThat(toList(guide.getUriQueryParam(getUri("http://localhost:8080/test?param2=value2"), "param1").iterator())).isEmpty();
    assertThat(toList(guide.getUriQueryParam(getUri("http://localhost:8080/test?param1=value1"), "param1").iterator()))
        .contains("value1");
    assertThat(toList(guide.getUriQueryParam(getUri("http://localhost:8080/test?param1=value1&param2=value2"), "param1").iterator()))
        .contains("value1");
    assertThat(toList(guide.getUriQueryParam(getUri("http://localhost:8080/test?param1=value1&param1=value2"), "param1").iterator()))
        .contains("value1", "value2");

    assertThat(toList(guide.getUriQueryParam(getUri("/test?param2=value2"), "param1").iterator())).isEmpty();
    assertThat(toList(guide.getUriQueryParam(getUri("/test?param1=value1"), "param1").iterator()))
        .contains("value1");
    assertThat(toList(guide.getUriQueryParam(getUri("/test?param1=value1&param2=value2"), "param1").iterator()))
        .contains("value1");
    assertThat(toList(guide.getUriQueryParam(getUri("/test?param1=value1&param1=value2"), "param1").iterator()))
        .contains("value1", "value2");
  }
}
