package tech.intellispaces.ixora.data.json.jackson;

import tech.intellispaces.ixora.data.json.JsonStringToPropertiesSetGuide;
import tech.intellispaces.ixora.data.json.JsonStringToPropertiesSetGuideTest;

/**
 * Tests for {@link JacksonGuide} class.
 */
public class JacksonGuideJsonStringToPropertiesSetTest extends JsonStringToPropertiesSetGuideTest {

  @Override
  public JsonStringToPropertiesSetGuide guide() {
    var jacksonGuide = new JacksonGuide();
    return jacksonGuide::jsonStringToPropertiesSet;
  }
}
