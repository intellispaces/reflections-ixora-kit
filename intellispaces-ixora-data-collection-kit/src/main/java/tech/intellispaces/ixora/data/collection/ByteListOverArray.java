package tech.intellispaces.ixora.data.collection;

import tech.intellispaces.commons.collection.ArraysFunctions;
import tech.intellispaces.commons.type.Type;
import tech.intellispaces.commons.type.Types;
import tech.intellispaces.jaquarius.annotation.Mapper;
import tech.intellispaces.jaquarius.annotation.ObjectHandle;

import java.util.List;

@ObjectHandle(ByteListDomain.class)
abstract class ByteListOverArray implements UnmovableByteList {
  private final Type<Byte> elementType = Types.get(Byte.class);
  private final byte[] array;
  private List<Byte> list;

  ByteListOverArray(byte[] array) {
    this.array = array;
  }

  ByteListOverArray(List<Byte> list) {
    this.array = ArraysFunctions.toByteArray(list);
    this.list = list;
  }

  public byte[] array() {
    return array;
  }

  @Mapper
  @Override
  public UnmovableCollectionHandle<Byte> asCollection() {
    return new JavaCollectionWrapper<>(nativeList(), elementType);
  }

  @Mapper
  @Override
  public Type<Byte> elementDomain() {
    return elementType;
  }

  @Mapper
  @Override
  public Byte get(int index) {
    return getElement(index);
  }

  @Mapper
  @Override
  public byte getAsPrimitive(int index) {
    return getElement(index);
  }

  private byte getElement(int index) {
    return array[index];
  }

  @Mapper
  @Override
  public int size() {
    return array.length;
  }

  @Override
  public List<Byte> nativeList() {
    if (list == null) {
      list = ArraysFunctions.toByteList(array);
    }
    return list;
  }

  @Override
  public java.util.Collection<Byte> nativeCollection() {
    return List.of();
  }
}

