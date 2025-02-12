package tech.intellispaces.jaquarius.ixora.data.stream;

import tech.intellispaces.jaquarius.ixora.data.stream.MovableByteInputStreamHandle;

import java.io.InputStream;

public interface ByteStreams {

  static MovableByteInputStreamHandle get(InputStream is) {
    return new JavaByteInputStreamHandleImpl(is);
  }

  static MovableByteInputStreamHandle empty() {
    return get(InputStream.nullInputStream());
  }
}
