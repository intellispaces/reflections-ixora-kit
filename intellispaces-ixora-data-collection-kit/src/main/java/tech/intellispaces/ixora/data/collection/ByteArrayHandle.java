package tech.intellispaces.ixora.data.collection;

import tech.intellispaces.commons.collection.ArraysFunctions;
import tech.intellispaces.commons.type.Type;
import tech.intellispaces.commons.type.Types;
import tech.intellispaces.jaquarius.annotation.Mapper;
import tech.intellispaces.jaquarius.annotation.ObjectHandle;

import java.util.Iterator;
import java.util.List;

@ObjectHandle(ByteListDomain.class)
abstract class ByteArrayHandle implements UnmovableByteListHandle {
  private final Type<Byte> elementType = Types.get(Byte.class);
  private final byte[] array;
  private List<Byte> list;

  ByteArrayHandle(byte[] array) {
    this.array = array;
  }

  ByteArrayHandle(List<Byte> list) {
    this.array = ArraysFunctions.toByteArray(list);
    this.list = list;
  }

  public byte[] array() {
    return array;
  }

  @Mapper
  @Override
  public UnmovableCollectionHandle<Byte> asCollection() {
    return new JavaCollectionHandleWrapper<>(list(), elementType);
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
  public Iterator<Byte> iterator() {
    return list().iterator();
  }

  private List<Byte> list() {
    if (list == null) {
      list = ArraysFunctions.toByteList(array);
    }
    return list;
  }
}

