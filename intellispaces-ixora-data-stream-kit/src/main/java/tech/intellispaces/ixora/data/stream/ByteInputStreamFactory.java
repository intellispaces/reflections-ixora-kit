package tech.intellispaces.ixora.data.stream;

import tech.intellispaces.jaquarius.annotation.Factory;

import java.io.InputStream;

@Factory
public class ByteInputStreamFactory implements ByteInputStreamAssistantCustomizer {

  @Override
  public MovableByteInputStreamHandle empty() {
    return handleOf(InputStream.nullInputStream());
  }

  @Override
  public MovableByteInputStreamHandle handleOf(InputStream is) {
    return new JavaByteInputStreamHandleWrapper(is);
  }
}
