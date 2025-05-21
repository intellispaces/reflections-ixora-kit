package tech.intellispaces.ixora.internet.uri;

import java.util.Arrays;

import tech.intellispaces.ixora.data.collection.ListReflection;
import tech.intellispaces.ixora.data.collection.Lists;
import tech.intellispaces.reflections.framework.annotation.Guide;
import tech.intellispaces.reflections.framework.annotation.Mapper;

@Guide
public class SplitUriPathStringToPartsGuideImpl implements SplitUriPathStringToPartsGuide {
  private static final String SLASH = "/";

  @Mapper
  @Override
  public ListReflection<String> splitUriPathStringToParts(String uriPath) {
    if (uriPath == null) {
      return null;
    }
    String path = uriPath.startsWith(SLASH) ? uriPath.substring(1) : uriPath;
    path = path.endsWith(SLASH) ? path.substring(0, path.length() - 1) : path;
    return Lists.reflectionOf(Arrays.asList(path.split(SLASH)), String.class);
  }
}
