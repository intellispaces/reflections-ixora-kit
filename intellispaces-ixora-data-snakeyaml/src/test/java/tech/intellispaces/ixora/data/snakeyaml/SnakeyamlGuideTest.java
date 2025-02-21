package tech.intellispaces.ixora.data.snakeyaml;

import tech.intellispaces.ixora.data.yaml.StringToDictionaryGuideTest;
import tech.intellispaces.ixora.data.yaml.StringToDictionaryGuide;

/**
 * Tests for {@link SnakeyamlGuide} class.
 */
public class SnakeyamlGuideTest extends StringToDictionaryGuideTest {

  @Override
  public StringToDictionaryGuide guide() {
    return new SnakeyamlGuide();
  }
}
