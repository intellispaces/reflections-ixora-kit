package tech.intellispaces.ixora.data.json.jackson;

import tech.intellispaces.ixora.data.json.JsonStringToPropertiesSetGuide;
import tech.intellispaces.ixora.data.json.JsonStringToPropertiesSetGuideTest;

/**
 * Tests for {@link JacksonGuide} class.
 */
public class JacksonGuideTest extends JsonStringToPropertiesSetGuideTest {

  @Override
  public JsonStringToPropertiesSetGuide guide() {
    return new JacksonGuide();
  }
}
