package tech.intellispaces.ixora.data.association;

import org.junit.jupiter.api.Test;

import tech.intellispaces.commons.type.Types;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Tests for guide {@link PropertiesSetToDataGuide}.
 */
public abstract class AbstractPropertiesSetToDataGuideTest {

  public abstract PropertiesSetToDataGuide getGuide();

  @Test
  public void testPrimitiveData_whenEmptyProperties() {
    // Given
    PropertiesSet props = mock(PropertiesSet.class);

    // When
    PrimitiveData data = getGuide().propertiesSetToData(props, Types.get(PrimitiveData.class));

    // Then
    assertThat(data).isNotNull();
    assertThat(data.intValue()).isNull();
    assertThat(data.doubleValue()).isNull();
  }

  @Test
  public void testSimpleData_whenEmptyProperties() {
    // Given
    PropertiesSet props = mock(PropertiesSet.class);

    // When
    SimpleData data = getGuide().propertiesSetToData(props, Types.get(SimpleData.class));

    // Then
    assertThat(data).isNotNull();
    assertThat(data.intValue()).isNull();
    assertThat(data.doubleValue()).isNull();
    assertThat(data.stringValue()).isNull();
  }

  @Test
  public void testNestedData_whenEmptyProperties() {
    // Given
    PropertiesSet props = mock(PropertiesSet.class);

    // When
    NestedData data = getGuide().propertiesSetToData(props, Types.get(NestedData.class));

    // Then
    assertThat(data).isNotNull();
    assertThat(data.stringValue()).isNull();
    assertThat(data.nestedValue()).isNull();
  }

  @Test
  public void testPrimitiveData_whenNotEmptyProperties() {
    // Given
    PropertiesSet props = mock(PropertiesSet.class);
    when(props.value("intValue")).thenReturn(1);
    when(props.value("doubleValue")).thenReturn(2.2);

    // When
    PrimitiveData data = getGuide().propertiesSetToData(props, Types.get(PrimitiveData.class));

    // Then
    assertThat(data).isNotNull();
    assertThat(data.intValue()).isEqualTo(1);
    assertThat(data.doubleValue()).isEqualTo(2.2);
  }

  @Test
  public void testSimpleData_whenNotEmptyProperties() {
    // Given
    PropertiesSet props = mock(PropertiesSet.class);
    when(props.value("intValue")).thenReturn(1);
    when(props.value("doubleValue")).thenReturn(2.2);
    when(props.value("stringValue")).thenReturn("abc");

    // When
    SimpleData data = getGuide().propertiesSetToData(props, Types.get(SimpleData.class));

    // Then
    assertThat(data).isNotNull();
    assertThat(data.intValue()).isEqualTo(1);
    assertThat(data.doubleValue()).isEqualTo(2.2);
    assertThat(data.stringValue()).isEqualTo("abc");
  }

  @Test
  public void testNestedData_whenNotEmptyProperties() {
    // Given
    PropertiesSet props = mock(PropertiesSet.class);
    PropertiesSet nestedProps = mock(PropertiesSet.class);
    when(props.value("stringValue")).thenReturn("abc");
    when(props.value("nestedValue")).thenReturn(nestedProps);
    when(nestedProps.value("stringValue")).thenReturn("def");

    // When
    NestedData data = getGuide().propertiesSetToData(props, Types.get(NestedData.class));

    // Then
    assertThat(data).isNotNull();
    assertThat(data.stringValue()).isEqualTo("abc");
    assertThat(data.nestedValue()).isNotNull();
    assertThat(data.nestedValue().stringValue()).isEqualTo("def");
    assertThat(data.nestedValue().nestedValue()).isNull();
  }
}
