package tech.intellispaces.ixora.data.stream;

import java.io.InputStream;

import tech.intellispaces.reflections.framework.annotation.Factory;

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
