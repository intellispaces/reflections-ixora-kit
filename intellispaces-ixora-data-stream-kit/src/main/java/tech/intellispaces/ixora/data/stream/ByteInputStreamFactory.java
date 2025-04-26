package tech.intellispaces.ixora.data.stream;

import java.io.InputStream;

import tech.intellispaces.jaquarius.annotation.Factory;

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
