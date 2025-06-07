package tech.intellispaces.ixora.data.association;

import java.util.Collections;

import tech.intellispaces.commons.type.Type;
import tech.intellispaces.commons.type.Types;
import tech.intellispaces.ixora.data.collection.Collection;
import tech.intellispaces.reflections.framework.annotation.Mapper;
import tech.intellispaces.reflections.framework.annotation.Reflection;
import tech.intellispaces.reflections.framework.reflection.NativeReflection;

@Reflection(domainClass = MapDomain.class)
abstract class JavaMapReflection<K, V> implements Map<K, V>, NativeReflection {
  private final java.util.Map<K, V> map;
  private final Type<K> keyType;
  private final Type<V> valueType;

  JavaMapReflection(java.util.Map<K, V> map, Class<K> keyClass, Class<V> valueClass) {
    this.map = Collections.unmodifiableMap(map);
    this.keyType = Types.get(keyClass);
    this.valueType = Types.get(valueClass);
  }

  JavaMapReflection(java.util.Map<K, V> map, Type<K> keyType, Type<V> valueType) {
    this.map = Collections.unmodifiableMap(map);
    this.keyType = keyType;
    this.valueType = valueType;
  }

  @Override
  public java.util.Map<K, V> boundObject() {
    return map;
  }

  @Override
  public Collection<KeyValuePair<K, V>> keyValuePairs() {
    throw new RuntimeException("Not implemented");
  }

  @Mapper
  @Override
  public boolean containsKey(K key) {
    return map.containsKey(key);
  }

  @Mapper
  @Override
  public V value(K key) {
    return map.get(key);
  }
}
