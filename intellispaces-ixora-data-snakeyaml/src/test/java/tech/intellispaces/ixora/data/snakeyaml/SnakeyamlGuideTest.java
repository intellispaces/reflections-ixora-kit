package tech.intellispaces.ixora.data.snakeyaml;

import tech.intellispaces.ixora.data.yaml.StringToDictionaryGuideTest;
import tech.intellispaces.ixora.data.yaml.YamlStringToPropertiesGuide;

/**
 * Tests for {@link SnakeyamlGuide} class.
 */
public class SnakeyamlGuideTest extends StringToDictionaryGuideTest {

  @Override
  public YamlStringToPropertiesGuide guide() {
    return new SnakeyamlGuide();
  }
}
