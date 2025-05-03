package tech.intellispaces.ixora.internet.uri;

import static org.assertj.core.api.Assertions.assertThat;
import static tech.intellispaces.commons.collection.CollectionFunctions.toList;

public abstract class AbstractSplitUriPathStringToPartsGuideTest {

  abstract SplitUriPathStringToPartsGuide getGuide();

  public void SplitUriPathStringToPartsGuide() {
    SplitUriPathStringToPartsGuide guide = getGuide();

    assertThat(guide.splitUriPathStringToParts(null)).isNull();
    assertThat(toList(guide.splitUriPathStringToParts("").iterator())).containsExactly("");
    assertThat(toList(guide.splitUriPathStringToParts("/").iterator())).containsExactly("");
    assertThat(toList(guide.splitUriPathStringToParts("/b").iterator())).containsExactly("b");
    assertThat(toList(guide.splitUriPathStringToParts("/a/b").iterator())).containsExactly("a", "b");
    assertThat(toList(guide.splitUriPathStringToParts("/a/b/").iterator())).containsExactly("a", "b");
    assertThat(toList(guide.splitUriPathStringToParts("/a//b/").iterator())).containsExactly("a", "", "b");
  }
}
