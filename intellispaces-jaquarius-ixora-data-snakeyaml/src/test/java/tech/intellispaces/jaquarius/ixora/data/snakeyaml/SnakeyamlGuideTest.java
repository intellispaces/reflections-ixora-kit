package tech.intellispaces.jaquarius.ixora.data.snakeyaml;

import tech.intellispaces.jaquarius.ixora.data.yaml.StringToDictionaryGuideTest;
import tech.intellispaces.jaquarius.ixora.data.yaml.StringToDictionaryGuide;

/**
 * Tests for {@link SnakeyamlGuide} class.
 */
public class SnakeyamlGuideTest extends StringToDictionaryGuideTest {

  @Override
  public StringToDictionaryGuide guide() {
    return new SnakeyamlGuide();
  }
}
