package tech.intellispaces.ixora.jetty;

import java.io.IOException;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import tech.intellispaces.commons.collection.ArraysFunctions;
import tech.intellispaces.ixora.http.HttpMethods;
import tech.intellispaces.ixora.http.HttpPortExchangeChannel;
import tech.intellispaces.ixora.http.HttpRequestReflection;
import tech.intellispaces.ixora.http.HttpRequests;
import tech.intellispaces.ixora.http.UnmovableHttpResponseReflection;
import tech.intellispaces.reflections.framework.space.channel.ChannelFunctions;

import static tech.intellispaces.commons.collection.CollectionFunctions.toList;

class JettyServlet extends HttpServlet {
  private MovableJettyServerPortReflection port;
  private String httpPortExchangeChannelCid;

  void init(MovableJettyServerPortReflection port) {
    this.port = port;
    this.httpPortExchangeChannelCid = ChannelFunctions.getChannelId(HttpPortExchangeChannel.class);
  }

  @Override
  protected void doGet(
      HttpServletRequest servletRequest, HttpServletResponse servletResponse
  ) throws IOException {
    HttpRequestReflection req = requestReflection(servletRequest);
    UnmovableHttpResponseReflection res = port.mapOfMovingThru(httpPortExchangeChannelCid, req);
    populateResponse(servletResponse, res);
  }

  private HttpRequestReflection requestReflection(HttpServletRequest req) {
    String url = req.getRequestURL().toString();
    String query = req.getQueryString();
    String uri = (query == null ? url : url + '?' + query);
    return HttpRequests.create(HttpMethods.get(req.getMethod()), uri);
  }

  private void populateResponse(
      HttpServletResponse servletResponse, UnmovableHttpResponseReflection responseReflection
  ) throws IOException {
    if (responseReflection.status().isOkStatus()) {
      servletResponse.setStatus(HttpServletResponse.SC_OK);
    } else {
      throw new RuntimeException();
    }

    byte[] body = ArraysFunctions.toByteArray(toList(responseReflection.bodyStream().readAll().iterator()));
    servletResponse.getOutputStream().write(body);
  }
}
