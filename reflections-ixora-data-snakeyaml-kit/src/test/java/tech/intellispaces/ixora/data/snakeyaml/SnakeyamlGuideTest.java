package tech.intellispaces.ixora.data.snakeyaml;

import tech.intellispaces.ixora.data.yaml.YamlStringToPropertiesSetGuide;
import tech.intellispaces.ixora.data.yaml.YamlStringToPropertiesSetGuideTest;

/**
 * Tests for {@link SnakeyamlGuide} class.
 */
public class SnakeyamlGuideTest extends YamlStringToPropertiesSetGuideTest {

  @Override
  public YamlStringToPropertiesSetGuide guide() {
    return new SnakeyamlGuide();
  }
}
