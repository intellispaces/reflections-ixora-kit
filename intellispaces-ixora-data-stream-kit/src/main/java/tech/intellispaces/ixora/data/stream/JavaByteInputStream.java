package tech.intellispaces.ixora.data.stream;

import tech.intellispaces.commons.exception.UnexpectedExceptions;
import tech.intellispaces.commons.type.Type;
import tech.intellispaces.commons.type.Types;
import tech.intellispaces.ixora.data.collection.Lists;
import tech.intellispaces.ixora.data.collection.UnmovableByteList;
import tech.intellispaces.jaquarius.annotation.Mapper;
import tech.intellispaces.jaquarius.annotation.MapperOfMoving;
import tech.intellispaces.jaquarius.annotation.ObjectHandle;
import tech.intellispaces.jaquarius.exception.TraverseExceptions;
import tech.intellispaces.jaquarius.object.reference.MovableObjectHandle;

import java.io.IOException;
import java.io.InputStream;

@ObjectHandle(ByteInputStreamDomain.class)
abstract class JavaByteInputStream implements MovableByteInputStream, MovableObjectHandle<ByteInputStreamDomain> {
  private final InputStream is;
  private int buffer;
  private boolean buffered;

  JavaByteInputStream(InputStream is) {
    this.is = is;
  }

  @Override
  public void release() {
    try {
      is.close();
    } catch (IOException e) {
      throw UnexpectedExceptions.withCauseAndMessage(e, "Could not close input stream");
    }
  }

  @Mapper
  @Override
  public Type<Byte> elementDomain() {
    return Types.get(Byte.class);
  }

  @Mapper
  @Override
  public boolean isExhausted() {
    return hasNextElement();
  }

  @Override
  @MapperOfMoving
  public Byte read() {
    return nextByte();
  }

  @Override
  @MapperOfMoving
  public byte readAsPrimitive() {
    return nextByte();
  }

  @Override
  @MapperOfMoving
  public UnmovableByteList readMultiple(int number) {
    return Lists.ofBytes(nextBytes(number));
  }

  @Override
  @MapperOfMoving
  public UnmovableByteList readAll() {
    return Lists.ofBytes(allBytes());
  }

  private boolean hasNextElement() {
    if (buffered) {
      return true;
    }

    try {
      buffer = is.read();
      buffered = (buffer != -1);
      return buffered;
    } catch (IOException e) {
      throw TraverseExceptions.withCauseAndMessage(e, "Could not read the next element of input stream");
    }
  }

  private byte nextByte() {
    if (buffered) {
      buffered = false;
      return (byte) buffer;
    }

    try {
      buffer = is.read();
      if (buffer == -1) {
        throw TraverseExceptions.withMessage("The input stream is exhausted");
      }
      buffered = false;
      return (byte) buffer;
    } catch (IOException e) {
      throw TraverseExceptions.withCauseAndMessage(e, "Could not read the next element of input stream");
    }
  }

  private byte[] nextBytes(int number) {
    try {
      byte[] bytes = is.readNBytes(buffered ? number - 1 : number);
      if (!buffered) {
        return bytes;
      }

      byte[] allBytes = new byte[bytes.length + 1];
      allBytes[0] = (byte) buffer;
      System.arraycopy(bytes, 1, allBytes, 0, bytes.length);
      buffered = false;
      return allBytes;
    } catch (IOException e) {
      throw TraverseExceptions.withCauseAndMessage(e, "Could not read multiple bytes of input stream");
    }
  }

  private byte[] allBytes() {
    try {
      byte[] bytes = is.readAllBytes();
      if (!buffered) {
        return bytes;
      }

      byte[] allBytes = new byte[bytes.length + 1];
      allBytes[0] = (byte) buffer;
      System.arraycopy(bytes, 1, allBytes, 0, bytes.length);
      buffered = false;
      return allBytes;
    } catch (IOException e) {
      throw TraverseExceptions.withCauseAndMessage(e, "Could not read all bytes of input stream");
    }
  }
}
