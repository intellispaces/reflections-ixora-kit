package tech.intellispaces.ixora.data.stream;

import java.io.InputStream;

public interface ByteStreams {

  static MovableByteInputStream get(InputStream is) {
    return new JavaByteInputStreamWrapper(is);
  }

  static MovableByteInputStream empty() {
    return get(InputStream.nullInputStream());
  }
}
