package tech.intellispaces.ixora.data.json.jackson;

import tech.intellispaces.ixora.data.json.DatasetToJsonStringGuide;
import tech.intellispaces.ixora.data.json.DatasetToJsonStringGuideTest;

/**
 * Tests for {@link JacksonGuide} class.
 */
public class JacksonGuideDatasetToJsonStringTest extends DatasetToJsonStringGuideTest {

  @Override
  public DatasetToJsonStringGuide guide() {
    var jacksonGuide = new JacksonGuide();
    return jacksonGuide::datasetToJsonString;
  }
}
