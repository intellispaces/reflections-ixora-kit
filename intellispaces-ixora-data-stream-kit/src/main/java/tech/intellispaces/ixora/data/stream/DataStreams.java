package tech.intellispaces.ixora.data.stream;

import java.io.InputStream;

public interface DataStreams {

  static MovableByteInputStream get(InputStream is) {
    return ByteStreams.get(is);
  }

  static MovableByteInputStream emptyByteStream() {
    return ByteStreams.empty();
  }
}
