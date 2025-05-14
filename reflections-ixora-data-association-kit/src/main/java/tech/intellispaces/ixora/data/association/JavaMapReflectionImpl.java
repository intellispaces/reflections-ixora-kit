package tech.intellispaces.ixora.data.association;

import tech.intellispaces.commons.type.Type;
import tech.intellispaces.commons.type.Types;
import tech.intellispaces.ixora.data.collection.UnmovableCollectionReflection;
import tech.intellispaces.reflections.framework.annotation.Mapper;
import tech.intellispaces.reflections.framework.annotation.Reflection;

import java.util.Collections;
import java.util.Map;

@Reflection(MapDomain.class)
abstract class JavaMapReflectionImpl<K, V> implements UnmovableMap<K, V>, UnmovableMapReflection<K, V> {
  private final Map<K, V> map;
  private final Type<K> keyType;
  private final Type<V> valueType;

  JavaMapReflectionImpl(Map<K, V> map, Class<K> keyClass, Class<V> valueClass) {
    this.map = Collections.unmodifiableMap(map);
    this.keyType = Types.get(keyClass);
    this.valueType = Types.get(valueClass);
  }

  JavaMapReflectionImpl(Map<K, V> map, Type<K> keyType, Type<V> valueType) {
    this.map = Collections.unmodifiableMap(map);
    this.keyType = keyType;
    this.valueType = valueType;
  }

  Map<K, V> map() {
    return map;
  }

  @Override
  public Type<MapDomain<K, V>> domainType() {
    return Types.get(MapDomain.class, keyType, valueType);
  }

  @Override
  public UnmovableCollectionReflection<KeyValuePair<K, V>> keyValuePairs() {
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
