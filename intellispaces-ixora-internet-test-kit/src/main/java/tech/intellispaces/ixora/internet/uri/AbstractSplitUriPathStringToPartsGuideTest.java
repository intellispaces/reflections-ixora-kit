package tech.intellispaces.ixora.internet.uri;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class AbstractSplitUriPathStringToPartsGuideTest {

  abstract SplitUriPathStringToPartsGuide getGuide();

  public void SplitUriPathStringToPartsGuide() {
    SplitUriPathStringToPartsGuide guide = getGuide();

    assertThat(guide.splitUriPathStringToParts(null)).isNull();
    assertThat(guide.splitUriPathStringToParts("").nativeList()).containsExactly("");
    assertThat(guide.splitUriPathStringToParts("/").nativeList()).containsExactly("");
    assertThat(guide.splitUriPathStringToParts("/b").nativeList()).containsExactly("b");
    assertThat(guide.splitUriPathStringToParts("/a/b").nativeList()).containsExactly("a", "b");
    assertThat(guide.splitUriPathStringToParts("/a/b/").nativeList()).containsExactly("a", "b");
    assertThat(guide.splitUriPathStringToParts("/a//b/").nativeList()).containsExactly("a", "", "b");
  }
}
