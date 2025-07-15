package tech.intellispaces.ixora.data.json;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tech.intellispaces.reflections.framework.ReflectionsFramework;
import tech.intellispaces.reflections.framework.system.ReflectionModule;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for guide {@link DatasetToJsonStringGuide}.
 */
public abstract class DatasetToJsonStringGuideTest {
  private ReflectionModule module;

  @BeforeEach
  public void init() {
    module = ReflectionsFramework.loadModule().start();
  }

  @AfterEach
  public void destroy() {
    module.stop().upload();
  }

  public abstract DatasetToJsonStringGuide guide();

  @Test
  public void testSimpleDataset() {
    // Given
    SimpleDataset dataset = SimpleDatasets.build()
        .stringAttribute("The string")
//        .intAttribute(123)
//        .doubleAttribute(3.14)
        .getUnmovable();

    // When
    String json = guide().traverse(dataset);

    // Then
    assertThat(json).isEqualTo("{\"stringAttribute\":\"The string\"}");
  }
}
