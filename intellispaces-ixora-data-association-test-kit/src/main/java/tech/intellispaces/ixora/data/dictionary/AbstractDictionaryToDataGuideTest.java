package tech.intellispaces.ixora.data.dictionary;

import org.junit.jupiter.api.Test;
import tech.intellispaces.commons.type.Types;
import tech.intellispaces.ixora.data.association.Properties;
import tech.intellispaces.ixora.data.association.PropertiesToDataGuide;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Tests for guide {@link PropertiesToDataGuide}.
 */
public abstract class AbstractDictionaryToDataGuideTest {

  public abstract PropertiesToDataGuide getGuide();

  @Test
  public void testPrimitiveData_whenEmptyProperties() {
    // Given
    Properties dictionary = mock(Properties.class);

    // When
    PrimitiveData data = getGuide().propertiesToData(dictionary, Types.get(PrimitiveData.class));

    // Then
    assertThat(data).isNotNull();
    assertThat(data.intValue()).isNull();
    assertThat(data.doubleValue()).isNull();
  }

  @Test
  public void testSimpleData_whenEmptyProperties() {
    // Given
    Properties dictionary = mock(Properties.class);

    // When
    SimpleData data = getGuide().propertiesToData(dictionary, Types.get(SimpleData.class));

    // Then
    assertThat(data).isNotNull();
    assertThat(data.intValue()).isNull();
    assertThat(data.doubleValue()).isNull();
    assertThat(data.stringValue()).isNull();
  }

  @Test
  public void testNestedData_whenEmptyProperties() {
    // Given
    Properties dictionary = mock(Properties.class);

    // When
    NestedData data = getGuide().propertiesToData(dictionary, Types.get(NestedData.class));

    // Then
    assertThat(data).isNotNull();
    assertThat(data.stringValue()).isNull();
    assertThat(data.nestedValue()).isNull();
  }

  @Test
  public void testPrimitiveData_whenNotEmptyProperties() {
    // Given
    Properties dictionary = mock(Properties.class);
    when(dictionary.value("intValue")).thenReturn(1);
    when(dictionary.value("doubleValue")).thenReturn(2.2);

    // When
    PrimitiveData data = getGuide().propertiesToData(dictionary, Types.get(PrimitiveData.class));

    // Then
    assertThat(data).isNotNull();
    assertThat(data.intValue()).isEqualTo(1);
    assertThat(data.doubleValue()).isEqualTo(2.2);
  }

  @Test
  public void testSimpleData_whenNotEmptyProperties() {
    // Given
    Properties dictionary = mock(Properties.class);
    when(dictionary.value("intValue")).thenReturn(1);
    when(dictionary.value("doubleValue")).thenReturn(2.2);
    when(dictionary.value("stringValue")).thenReturn("abc");

    // When
    SimpleData data = getGuide().propertiesToData(dictionary, Types.get(SimpleData.class));

    // Then
    assertThat(data).isNotNull();
    assertThat(data.intValue()).isEqualTo(1);
    assertThat(data.doubleValue()).isEqualTo(2.2);
    assertThat(data.stringValue()).isEqualTo("abc");
  }

  @Test
  public void testNestedData_whenNotEmptyProperties() {
    // Given
    Properties dictionary = mock(Properties.class);
    Properties nestedDictionary = mock(Properties.class);
    when(dictionary.value("stringValue")).thenReturn("abc");
    when(dictionary.value("nestedValue")).thenReturn(nestedDictionary);
    when(nestedDictionary.value("stringValue")).thenReturn("def");

    // When
    NestedData data = getGuide().propertiesToData(dictionary, Types.get(NestedData.class));

    // Then
    assertThat(data).isNotNull();
    assertThat(data.stringValue()).isEqualTo("abc");
    assertThat(data.nestedValue()).isNotNull();
    assertThat(data.nestedValue().stringValue()).isEqualTo("def");
    assertThat(data.nestedValue().nestedValue()).isNull();
  }
}
