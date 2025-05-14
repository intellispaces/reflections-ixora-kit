package tech.intellispaces.ixora.data.stream;

import tech.intellispaces.reflections.framework.annotation.Factory;

import java.io.InputStream;

@Factory
public class ByteInputStreamFactory implements ByteInputStreamAssistantCustomizer {

  @Override
  public MovableByteInputStreamReflection empty() {
    return reflectionOf(InputStream.nullInputStream());
  }

  @Override
  public MovableByteInputStreamReflection reflectionOf(InputStream is) {
    return new JavaByteInputStreamReflectionImplWrapper(is);
  }
}
