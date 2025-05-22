package tech.intellispaces.ixora.data.json;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tech.intellispaces.core.Module;
import tech.intellispaces.ixora.data.association.PropertiesSet;
import tech.intellispaces.ixora.data.collection.List;
import tech.intellispaces.reflections.framework.ReflectionsFramework;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for guide {@link JsonStringToPropertiesSetGuide}.
 */
public abstract class JsonStringToPropertiesSetGuideTest {
  private Module module;

  @BeforeEach
  public void init() {
    module = ReflectionsFramework.loadModule().start();
  }

  @AfterEach
  public void destroy() {
    module.stop().upload();
  }

  public abstract JsonStringToPropertiesSetGuide guide();

  @Test
  public void testEmptyJson() {
    // Given
    String json = "{}";

    // When
    PropertiesSet props = guide().traverse(json);

    // Then
    assertThat(props).isNotNull();
    assertThat(props.size()).isEqualTo(0);
  }

  @Test
  public void testSimpleData() {
    // Given
    String json = """
        {
          "intValue": 1,
          "doubleValue": 2.2,
          "stringValue": "abc"
        }
        """;

    // When
    PropertiesSet props = guide().traverse(json);

    // Then
    assertThat(props).isNotNull();
    assertThat(props.size()).isEqualTo(3);

    assertThat(props.integer32Value("intValue")).isEqualTo(1);
    assertThat(props.value("intValue")).isEqualTo(1);

    assertThat(props.real64Value("doubleValue")).isEqualTo(2.2);
    assertThat(props.value("doubleValue")).isEqualTo(2.2);

    assertThat(props.stringValue("stringValue")).isEqualTo("abc");
    assertThat(props.value("stringValue")).isEqualTo("abc");
  }

  @Test
  @SuppressWarnings("unchecked")
  public void testIntegerList() {
    // Given
    String json = """
        {
          "values": [ 1, 2, 3 ]
        }
        """;

    // When
    PropertiesSet props = guide().traverse(json);

    // Then
    assertThat(props).isNotNull();
    assertThat(props.size()).isEqualTo(1);

    assertThat(props.integer32List("values").size()).isEqualTo(3);
    assertThat(props.integer32List("values").get(0)).isEqualTo(1);
    assertThat(props.integer32List("values").get(1)).isEqualTo(2);
    assertThat(props.integer32List("values").get(2)).isEqualTo(3);

    assertThat(((List<Integer>) props.value("values")).size()).isEqualTo(3);
    assertThat(((List<Integer>) props.value("values")).get(0)).isEqualTo(1);
    assertThat(((List<Integer>) props.value("values")).get(1)).isEqualTo(2);
    assertThat(((List<Integer>) props.value("values")).get(2)).isEqualTo(3);
  }

  @Test
  @SuppressWarnings("unchecked")
  public void testDoubleList() {
    // Given
    String json = """
        {
          "values": [ 1.1, 2.2, 3.3 ]
        }
        """;

    // When
    PropertiesSet props = guide().traverse(json);

    // Then
    assertThat(props).isNotNull();
    assertThat(props.size()).isEqualTo(1);

    assertThat(props.real64List("values").size()).isEqualTo(3);
    assertThat(props.real64List("values").get(0)).isEqualTo(1.1);
    assertThat(props.real64List("values").get(1)).isEqualTo(2.2);
    assertThat(props.real64List("values").get(2)).isEqualTo(3.3);

    assertThat(((List<Double>) props.value("values")).size()).isEqualTo(3);
    assertThat(((List<Double>) props.value("values")).get(0)).isEqualTo(1.1);
    assertThat(((List<Double>) props.value("values")).get(1)).isEqualTo(2.2);
    assertThat(((List<Double>) props.value("values")).get(2)).isEqualTo(3.3);
  }

  @Test
  @SuppressWarnings("unchecked")
  public void testStringList() {
    // Given
    String json = """
        {
          "values": [ "a", "b", "c" ]
        }
        """;

    // When
    PropertiesSet props = guide().traverse(json);

    // Then
    assertThat(props).isNotNull();
    assertThat(props.size()).isEqualTo(1);

    assertThat(props.stringList("values").size()).isEqualTo(3);
    assertThat(props.stringList("values").get(0)).isEqualTo("a");
    assertThat(props.stringList("values").get(1)).isEqualTo("b");
    assertThat(props.stringList("values").get(2)).isEqualTo("c");

    assertThat(((List<String>) props.value("values")).size()).isEqualTo(3);
    assertThat(((List<String>) props.value("values")).get(0)).isEqualTo("a");
    assertThat(((List<String>) props.value("values")).get(1)).isEqualTo("b");
    assertThat(((List<String>) props.value("values")).get(2)).isEqualTo("c");
  }

  @Test
  public void testNestedData() {
    // Given
    String json = """
        {
          "value1": 1,
          "nestedValue": {
            "value2": "abc"
          }
        }
        """;

    // When
    PropertiesSet props = guide().traverse(json);

    // Then
    assertThat(props).isNotNull();
    assertThat(props.size()).isEqualTo(2);
    assertThat(props.value("value1")).isEqualTo(1);
    assertThat(props.value("nestedValue.value2")).isEqualTo("abc");
    assertThat(props.propertiesSetValue("nestedValue").size()).isEqualTo(1);
  }
}
