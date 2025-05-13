package tech.intellispaces.ixora.data.stream;

import tech.intellispaces.reflections.framework.annotation.Factory;

import java.io.InputStream;

@Factory
public class ByteInputStreamFactory implements ByteInputStreamAssistantCustomizer {

  @Override
  public MovableByteInputStreamReflection empty() {
    return handleOf(InputStream.nullInputStream());
  }

  @Override
  public MovableByteInputStreamReflection handleOf(InputStream is) {
    return new JavaByteInputStreamReflectionWrapper(is);
  }
}
