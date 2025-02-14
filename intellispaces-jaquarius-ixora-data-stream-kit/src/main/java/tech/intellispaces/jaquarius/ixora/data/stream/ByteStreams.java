package tech.intellispaces.jaquarius.ixora.data.stream;

import java.io.InputStream;

public interface ByteStreams {

  static MovableByteInputStreamHandle get(InputStream is) {
    return new JavaByteInputStreamHandleWrapper(is);
  }

  static MovableByteInputStreamHandle empty() {
    return get(InputStream.nullInputStream());
  }
}
