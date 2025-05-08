package tech.intellispaces.ixora.internet.uri;

import tech.intellispaces.reflections.annotation.Guide;
import tech.intellispaces.reflections.annotation.Mapper;

@Guide
public class JoinBasePathStringWithEndpointStringGuideImpl implements JoinBasePathStringWithEndpointStringGuide {

  @Mapper
  @Override
  public String joinBasePathStringWithEndpointString(String baseUrl, String endpoint) {
    if (baseUrl == null) {
      return endpoint;
    } else {
      if (endpoint == null) {
        return baseUrl;
      }
    }

    if (baseUrl.endsWith("/")) {
      if (endpoint.startsWith("/")) {
        return baseUrl + endpoint.substring(1);
      } else {
        return baseUrl + endpoint;
      }
    } else {
      if (endpoint.startsWith("/")) {
        return baseUrl + endpoint;
      } else {
        return baseUrl + "/" + endpoint;
      }
    }
  }
}
