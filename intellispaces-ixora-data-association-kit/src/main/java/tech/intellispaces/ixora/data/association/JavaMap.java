package tech.intellispaces.ixora.data.association;

import tech.intellispaces.commons.type.Type;
import tech.intellispaces.commons.type.Types;
import tech.intellispaces.ixora.data.collection.UnmovableCollection;
import tech.intellispaces.jaquarius.annotation.ObjectHandle;
import tech.intellispaces.jaquarius.object.reference.UnmovableObjectHandle;

import java.util.Collections;
import java.util.Map;

@ObjectHandle(MapDomain.class)
abstract class JavaMap<K, V> implements UnmovableMap<K, V>, UnmovableObjectHandle<MapDomain<K, V>> {
  private final Map<K, V> map;
  private final Type<K> keyType;
  private final Type<V> valueType;

  JavaMap(Map<K, V> map, Class<K> keyClass, Class<V> valueClass) {
    this.map = Collections.unmodifiableMap(map);
    this.keyType = Types.get(keyClass);
    this.valueType = Types.get(valueClass);
  }

  JavaMap(Map<K, V> map, Type<K> keyType, Type<V> valueType) {
    this.map = Collections.unmodifiableMap(map);
    this.keyType = keyType;
    this.valueType = valueType;
  }

  Map<K, V> map() {
    return map;
  }

  @Override
  public Type<MapDomain<K, V>> domain() {
    return Types.get(MapDomain.class, keyType, valueType);
  }

  @Override
  public UnmovableCollection<KeyValuePair<K, V>> keyValuePairs() {
    throw new RuntimeException("Not implemented");
  }

  @Override
  public Map<K, V> nativeMap() {
    return map;
  }
}
