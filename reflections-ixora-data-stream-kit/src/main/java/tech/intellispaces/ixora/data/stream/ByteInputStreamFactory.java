package tech.intellispaces.ixora.data.stream;

import java.io.InputStream;

import tech.intellispaces.reflections.framework.annotation.Factory;

@Factory
public class ByteInputStreamFactory implements ByteInputStreamAssistantCustomizer {

  @Override
  public MovableByteInputStream empty() {
    return reflectionOf(InputStream.nullInputStream());
  }

  @Override
  public MovableByteInputStream reflectionOf(InputStream is) {
    return new JavaByteInputStreamReflectionWrapper(is);
  }
}
