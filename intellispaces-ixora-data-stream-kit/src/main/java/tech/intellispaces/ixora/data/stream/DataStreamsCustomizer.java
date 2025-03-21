package tech.intellispaces.ixora.data.stream;

import java.io.InputStream;

public interface DataStreamsCustomizer {

  static MovableByteInputStreamHandle get(InputStream is) {
    return ByteStreamsCustomizer.get(is);
  }

  static MovableByteInputStreamHandle emptyByteStream() {
    return ByteStreamsCustomizer.empty();
  }
}
