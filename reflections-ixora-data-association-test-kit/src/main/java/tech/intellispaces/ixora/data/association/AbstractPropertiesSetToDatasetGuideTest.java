package tech.intellispaces.ixora.data.association;

import org.junit.jupiter.api.Test;

import tech.intellispaces.commons.type.Types;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Tests for guide {@link PropertiesSetToDatasetGuide}.
 */
public abstract class AbstractPropertiesSetToDatasetGuideTest {

  public abstract PropertiesSetToDatasetGuide getGuide();

  @Test
  public void testPrimitiveData_whenEmptyProperties() {
    // Given
    PropertiesSet props = mock(PropertiesSet.class);

    // When
    PrimitiveDataDomain data = getGuide().propertiesSetToDataset(props, Types.get(PrimitiveDataDomain.class));

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
    SimpleDataDomain data = getGuide().propertiesSetToDataset(props, Types.get(SimpleDataDomain.class));

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
    NestedDataDomain data = getGuide().propertiesSetToDataset(props, Types.get(NestedDataDomain.class));

    // Then
    assertThat(data).isNotNull();
    assertThat(data.stringValue()).isNull();
    assertThat(data.nestedValue()).isNull();
  }

  @Test
  public void testPrimitiveData_whenNotEmptyProperties() {
    // Given
    PropertiesSet props = mock(PropertiesSet.class);
    when(props.property("intValue")).thenReturn(1);
    when(props.property("doubleValue")).thenReturn(2.2);

    // When
    PrimitiveDataDomain data = getGuide().propertiesSetToDataset(props, Types.get(PrimitiveDataDomain.class));

    // Then
    assertThat(data).isNotNull();
    assertThat(data.intValue()).isEqualTo(1);
    assertThat(data.doubleValue()).isEqualTo(2.2);
  }

  @Test
  public void testSimpleData_whenNotEmptyProperties() {
    // Given
    PropertiesSet props = mock(PropertiesSet.class);
    when(props.property("intValue")).thenReturn(1);
    when(props.property("doubleValue")).thenReturn(2.2);
    when(props.property("stringValue")).thenReturn("abc");

    // When
    SimpleDataDomain data = getGuide().propertiesSetToDataset(props, Types.get(SimpleDataDomain.class));

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
    when(props.property("stringValue")).thenReturn("abc");
    when(props.property("nestedValue")).thenReturn(nestedProps);
    when(nestedProps.property("stringValue")).thenReturn("def");

    // When
    NestedDataDomain data = getGuide().propertiesSetToDataset(props, Types.get(NestedDataDomain.class));

    // Then
    assertThat(data).isNotNull();
    assertThat(data.stringValue()).isEqualTo("abc");
    assertThat(data.nestedValue()).isNotNull();
    assertThat(data.nestedValue().stringValue()).isEqualTo("def");
    assertThat(data.nestedValue().nestedValue()).isNull();
  }
}
