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
    PrimitiveDataDomain data = getGuide().propertiesSetToData(props, Types.get(PrimitiveDataDomain.class));

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
    SimpleDataDomain data = getGuide().propertiesSetToData(props, Types.get(SimpleDataDomain.class));

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
    NestedDataDomain data = getGuide().propertiesSetToData(props, Types.get(NestedDataDomain.class));

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
    PrimitiveDataDomain data = getGuide().propertiesSetToData(props, Types.get(PrimitiveDataDomain.class));

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
    SimpleDataDomain data = getGuide().propertiesSetToData(props, Types.get(SimpleDataDomain.class));

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
    NestedDataDomain data = getGuide().propertiesSetToData(props, Types.get(NestedDataDomain.class));

    // Then
    assertThat(data).isNotNull();
    assertThat(data.stringValue()).isEqualTo("abc");
    assertThat(data.nestedValue()).isNotNull();
    assertThat(data.nestedValue().stringValue()).isEqualTo("def");
    assertThat(data.nestedValue().nestedValue()).isNull();
  }
}
