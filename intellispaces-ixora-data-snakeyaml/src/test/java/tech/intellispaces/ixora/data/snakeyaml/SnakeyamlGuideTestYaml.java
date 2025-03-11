package tech.intellispaces.ixora.data.snakeyaml;

import tech.intellispaces.ixora.data.yaml.YamlStringToDictionaryGuideTest;
import tech.intellispaces.ixora.data.yaml.YamlStringToPropertiesGuide;

/**
 * Tests for {@link SnakeyamlGuide} class.
 */
public class SnakeyamlGuideTestYaml extends YamlStringToDictionaryGuideTest {

  @Override
  public YamlStringToPropertiesGuide guide() {
    return new SnakeyamlGuide();
  }
}
