package tech.intellispaces.jaquarius.ixora.internet.uri;

import tech.intellispaces.commons.base.data.Pair;
import tech.intellispaces.commons.base.data.Pairs;
import tech.intellispaces.commons.base.text.StringFunctions;
import tech.intellispaces.jaquarius.annotation.Guide;
import tech.intellispaces.jaquarius.annotation.Mapper;
import tech.intellispaces.jaquarius.ixora.data.collection.ListHandle;
import tech.intellispaces.jaquarius.ixora.data.collection.Lists;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

@Guide
public class GetUriQueryParamGuideImpl implements GetUriQueryParamGuide {

  @Mapper
  @Override
  public ListHandle<String> getUriQueryParam(UriHandle uri, String paramName) {
    if (uri == null || paramName == null) {
      return Lists.empty(String.class);
    }
    String query = uri.query();
    if (StringFunctions.isNullOrBlank(query)) {
      return Lists.empty(String.class);
    }

    java.util.List<String> values = Arrays.stream(query.split("&"))
        .map(this::decode)
        .filter(p -> paramName.equals(p.value1()))
        .map(Pair::value2)
        .toList();
    return Lists.of(values, String.class);
  }

  private Pair<String, String> decode(String pair) {
    int ind = pair.indexOf("=");
    String key = (ind > 0 ? pair.substring(0, ind) : pair);
    String value = (ind > 0 && pair.length() > ind + 1 ? pair.substring(ind + 1) : null);
    return Pairs.get(
        URLDecoder.decode(key, StandardCharsets.UTF_8),
        value != null ? URLDecoder.decode(value, StandardCharsets.UTF_8) : value
    );
  }
}
