package tech.intellispaces.ixora.data.yaml;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tech.intellispaces.ixora.data.association.PropertiesSet;
import tech.intellispaces.ixora.data.collection.List;
import tech.intellispaces.reflections.framework.ReflectionsFramework;
import tech.intellispaces.reflections.framework.system.ReflectionModule;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for guide {@link YamlStringToPropertiesSetGuide}.
 */
public abstract class YamlStringToPropertiesSetGuideTest {
  private ReflectionModule module;

  @BeforeEach
  public void init() {
    module = ReflectionsFramework.loadModule().start();
  }

  @AfterEach
  public void destroy() {
    module.stop().upload();
  }

  public abstract YamlStringToPropertiesSetGuide guide();

  @Test
  public void testEmptyYaml() {
    // Given
    String yaml = "";

    // When
    PropertiesSet props = guide().traverse(yaml);

    // Then
    assertThat(props).isNotNull();
    assertThat(props.size()).isEqualTo(0);
  }

  @Test
  public void testSimpleData() {
    // Given
    String yaml = """
        intValue: 1
        doubleValue: 2.2
        stringValue: abc
        """;

    // When
    PropertiesSet props = guide().traverse(yaml);

    // Then
    assertThat(props).isNotNull();
    assertThat(props.size()).isEqualTo(3);

    assertThat(props.integer32Property("intValue")).isEqualTo(1);
    assertThat(props.property("intValue")).isEqualTo(1);

    assertThat(props.real64Property("doubleValue")).isEqualTo(2.2);
    assertThat(props.property("doubleValue")).isEqualTo(2.2);

    assertThat(props.stringProperty("stringValue")).isEqualTo("abc");
    assertThat(props.property("stringValue")).isEqualTo("abc");
  }

  @Test
  @SuppressWarnings("unchecked")
  public void testIntegerList() {
    // Given
    String yaml = """
        values:
          - 1
          - 2
          - 3
        """;

    // When
    PropertiesSet props = guide().traverse(yaml);

    // Then
    assertThat(props).isNotNull();
    assertThat(props.size()).isEqualTo(1);

    assertThat(props.integer32ListProperty("values").size()).isEqualTo(3);
    assertThat(props.integer32ListProperty("values").get(0)).isEqualTo(1);
    assertThat(props.integer32ListProperty("values").get(1)).isEqualTo(2);
    assertThat(props.integer32ListProperty("values").get(2)).isEqualTo(3);

    assertThat(((List<Integer>) props.property("values")).size()).isEqualTo(3);
    assertThat(((List<Integer>) props.property("values")).get(0)).isEqualTo(1);
    assertThat(((List<Integer>) props.property("values")).get(1)).isEqualTo(2);
    assertThat(((List<Integer>) props.property("values")).get(2)).isEqualTo(3);
  }

  @Test
  @SuppressWarnings("unchecked")
  public void testDoubleList() {
    // Given
    String yaml = """
        values:
          - 1.1
          - 2.2
          - 3.3
        """;

    // When
    PropertiesSet props = guide().traverse(yaml);

    // Then
    assertThat(props).isNotNull();
    assertThat(props.size()).isEqualTo(1);

    assertThat(props.real64ListProperty("values").size()).isEqualTo(3);
    assertThat(props.real64ListProperty("values").get(0)).isEqualTo(1.1);
    assertThat(props.real64ListProperty("values").get(1)).isEqualTo(2.2);
    assertThat(props.real64ListProperty("values").get(2)).isEqualTo(3.3);

    assertThat(((List<Double>) props.property("values")).size()).isEqualTo(3);
    assertThat(((List<Double>) props.property("values")).get(0)).isEqualTo(1.1);
    assertThat(((List<Double>) props.property("values")).get(1)).isEqualTo(2.2);
    assertThat(((List<Double>) props.property("values")).get(2)).isEqualTo(3.3);
  }

  @Test
  @SuppressWarnings("unchecked")
  public void testStringList() {
    // Given
    String yaml = """
        values:
          - "a"
          - "b"
          - "c"
        """;

    // When
    PropertiesSet props = guide().traverse(yaml);

    // Then
    assertThat(props).isNotNull();
    assertThat(props.size()).isEqualTo(1);

    assertThat(props.stringListProperty("values").size()).isEqualTo(3);
    assertThat(props.stringListProperty("values").get(0)).isEqualTo("a");
    assertThat(props.stringListProperty("values").get(1)).isEqualTo("b");
    assertThat(props.stringListProperty("values").get(2)).isEqualTo("c");

    assertThat(((List<String>) props.property("values")).size()).isEqualTo(3);
    assertThat(((List<String>) props.property("values")).get(0)).isEqualTo("a");
    assertThat(((List<String>) props.property("values")).get(1)).isEqualTo("b");
    assertThat(((List<String>) props.property("values")).get(2)).isEqualTo("c");
  }

  @Test
  public void testNestedData() {
    // Given
    String yaml = """
        value1: 1
        nestedValue:
          value2: abc
        """;

    // When
    PropertiesSet props = guide().traverse(yaml);

    // Then
    assertThat(props).isNotNull();
    assertThat(props.size()).isEqualTo(2);
    assertThat(props.property("value1")).isEqualTo(1);
    assertThat(props.property("nestedValue.value2")).isEqualTo("abc");
    assertThat(props.propertiesSetProperty("nestedValue").size()).isEqualTo(1);
  }
}
