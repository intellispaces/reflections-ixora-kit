package tech.intellispaces.ixora.data.stream;

import java.io.InputStream;

public interface ByteStreamsCustomizer {

  static MovableByteInputStreamHandle get(InputStream is) {
    return new JavaByteInputStreamWrapper(is);
  }

  static MovableByteInputStreamHandle empty() {
    return get(InputStream.nullInputStream());
  }
}
