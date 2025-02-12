package tech.intellispaces.jaquarius.ixora.data.stream;

import java.io.InputStream;

public interface DataStreams {

  static MovableByteInputStreamHandle get(InputStream is) {
    return ByteStreams.get(is);
  }

  static MovableByteInputStreamHandle emptyByteStream() {
    return ByteStreams.empty();
  }
}
