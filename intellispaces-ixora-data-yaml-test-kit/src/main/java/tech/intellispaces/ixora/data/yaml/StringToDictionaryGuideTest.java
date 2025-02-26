package tech.intellispaces.ixora.data.yaml;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tech.intellispaces.ixora.data.association.PropertiesHandle;
import tech.intellispaces.ixora.data.collection.ListHandle;
import tech.intellispaces.jaquarius.system.Modules;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for guide {@link YamlStringToPropertiesGuide}.
 */
public abstract class StringToDictionaryGuideTest {

  @BeforeEach
  public void init() {
    Modules.load().start();
  }

  @AfterEach
  public void destroy() {
    Modules.unload();
  }

  public abstract YamlStringToPropertiesGuide guide();

  @Test
  public void testEmptyYaml() {
    // Given
    String yaml = "";

    // When
    PropertiesHandle properties = guide().yamlStringToProperties(yaml);

    // Then
    assertThat(properties).isNotNull();
    assertThat(properties.size()).isEqualTo(0);
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
    PropertiesHandle properties = guide().yamlStringToProperties(yaml);

    // Then
    assertThat(properties).isNotNull();
    assertThat(properties.size()).isEqualTo(3);

    assertThat(properties.integer32Value("intValue")).isEqualTo(1);
    assertThat(properties.value("intValue")).isEqualTo(1);

    assertThat(properties.float64Value("doubleValue")).isEqualTo(2.2);
    assertThat(properties.value("doubleValue")).isEqualTo(2.2);

    assertThat(properties.stringValue("stringValue")).isEqualTo("abc");
    assertThat(properties.value("stringValue")).isEqualTo("abc");
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
    PropertiesHandle properties = guide().yamlStringToProperties(yaml);

    // Then
    assertThat(properties).isNotNull();
    assertThat(properties.size()).isEqualTo(1);

    assertThat(properties.integer32List("values").size()).isEqualTo(3);
    assertThat(properties.integer32List("values").get(0)).isEqualTo(1);
    assertThat(properties.integer32List("values").get(1)).isEqualTo(2);
    assertThat(properties.integer32List("values").get(2)).isEqualTo(3);

    assertThat(((ListHandle<Integer>) properties.value("values")).size()).isEqualTo(3);
    assertThat(((ListHandle<Integer>) properties.value("values")).get(0)).isEqualTo(1);
    assertThat(((ListHandle<Integer>) properties.value("values")).get(1)).isEqualTo(2);
    assertThat(((ListHandle<Integer>) properties.value("values")).get(2)).isEqualTo(3);
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
    PropertiesHandle properties = guide().yamlStringToProperties(yaml);

    // Then
    assertThat(properties).isNotNull();
    assertThat(properties.size()).isEqualTo(1);

    assertThat(properties.float64List("values").size()).isEqualTo(3);
    assertThat(properties.float64List("values").get(0)).isEqualTo(1.1);
    assertThat(properties.float64List("values").get(1)).isEqualTo(2.2);
    assertThat(properties.float64List("values").get(2)).isEqualTo(3.3);

    assertThat(((ListHandle<Double>) properties.value("values")).size()).isEqualTo(3);
    assertThat(((ListHandle<Double>) properties.value("values")).get(0)).isEqualTo(1.1);
    assertThat(((ListHandle<Double>) properties.value("values")).get(1)).isEqualTo(2.2);
    assertThat(((ListHandle<Double>) properties.value("values")).get(2)).isEqualTo(3.3);
  }

  @Test
  @SuppressWarnings("unchecked")
  public void testStringList() {
    // Given
    String yaml = """
        values:
          - a
          - "b"
          - c
        """;

    // When
    PropertiesHandle properties = guide().yamlStringToProperties(yaml);

    // Then
    assertThat(properties).isNotNull();
    assertThat(properties.size()).isEqualTo(1);

    assertThat(properties.stringList("values").size()).isEqualTo(3);
    assertThat(properties.stringList("values").get(0)).isEqualTo("a");
    assertThat(properties.stringList("values").get(1)).isEqualTo("b");
    assertThat(properties.stringList("values").get(2)).isEqualTo("c");

    assertThat(((ListHandle<String>) properties.value("values")).size()).isEqualTo(3);
    assertThat(((ListHandle<String>) properties.value("values")).get(0)).isEqualTo("a");
    assertThat(((ListHandle<String>) properties.value("values")).get(1)).isEqualTo("b");
    assertThat(((ListHandle<String>) properties.value("values")).get(2)).isEqualTo("c");
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
    PropertiesHandle properties = guide().yamlStringToProperties(yaml);

    // Then
    assertThat(properties).isNotNull();
    assertThat(properties.size()).isEqualTo(2);
    assertThat(properties.value("value1")).isEqualTo(1);
    assertThat(properties.value("nestedValue.value2")).isEqualTo("abc");
    assertThat(properties.propertiesValue("nestedValue").size()).isEqualTo(1);
  }
}
