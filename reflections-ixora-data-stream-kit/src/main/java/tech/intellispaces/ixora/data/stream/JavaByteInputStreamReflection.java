package tech.intellispaces.ixora.data.stream;

import java.io.IOException;
import java.io.InputStream;

import tech.intellispaces.commons.exception.UnexpectedExceptions;
import tech.intellispaces.commons.type.Type;
import tech.intellispaces.commons.type.Types;
import tech.intellispaces.ixora.data.collection.ByteList;
import tech.intellispaces.ixora.data.collection.ByteLists;
import tech.intellispaces.reflections.framework.annotation.Mapper;
import tech.intellispaces.reflections.framework.annotation.MapperOfMoving;
import tech.intellispaces.reflections.framework.annotation.Reflection;
import tech.intellispaces.reflections.framework.exception.TraverseExceptions;
import tech.intellispaces.reflections.framework.reflection.NativeReflection;

@Reflection(domainClass = ByteInputStreamDomain.class)
abstract class JavaByteInputStreamReflection implements MovableByteInputStream, NativeReflection {
  private final InputStream is;
  private int buffer;
  private boolean buffered;

  JavaByteInputStreamReflection(InputStream is) {
    this.is = is;
  }

  @Override
  public InputStream boundObject() {
    return is;
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
  public ByteList readMultiple(int number) {
    return ByteLists.reflectionOf(nextBytes(number));
  }

  @Override
  @MapperOfMoving
  public ByteList readAll() {
    return ByteLists.reflectionOf(allBytes());
  }

  @Override
  public void unbind() {
    try {
      is.close();
    } catch (IOException e) {
      throw UnexpectedExceptions.withCauseAndMessage(e, "Could not close input stream");
    }
  }

  @Override
  public void close() {
    unbind();
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
