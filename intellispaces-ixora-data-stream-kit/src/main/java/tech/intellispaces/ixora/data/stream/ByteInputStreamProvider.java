package tech.intellispaces.ixora.data.stream;

import tech.intellispaces.jaquarius.annotation.ObjectProvider;
import tech.intellispaces.jaquarius.ixora.data.stream.ByteInputStreamProviderCustomizer;

import java.io.InputStream;

@ObjectProvider
public class ByteInputStreamProvider implements ByteInputStreamProviderCustomizer {

  @Override
  public MovableByteInputStreamHandle empty() {
    return handleOf(InputStream.nullInputStream());
  }

  @Override
  public MovableByteInputStreamHandle handleOf(InputStream is) {
    return new JavaByteInputStreamHandleWrapper(is);
  }
}
