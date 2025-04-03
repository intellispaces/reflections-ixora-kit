package tech.intellispaces.ixora.data.stream;

import tech.intellispaces.jaquarius.annotation.ObjectFactory;
import tech.intellispaces.jaquarius.ixora.data.stream.ByteInputStreamAssistantExtension;

import java.io.InputStream;

@ObjectFactory
public class ByteInputStreamFactory implements ByteInputStreamAssistantExtension {

  @Override
  public MovableByteInputStreamHandle empty() {
    return handleOf(InputStream.nullInputStream());
  }

  @Override
  public MovableByteInputStreamHandle handleOf(InputStream is) {
    return new JavaByteInputStreamHandleWrapper(is);
  }
}
