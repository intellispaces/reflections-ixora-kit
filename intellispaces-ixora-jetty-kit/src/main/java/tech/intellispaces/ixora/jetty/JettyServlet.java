package tech.intellispaces.ixora.jetty;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tech.intellispaces.commons.collection.ArraysFunctions;
import tech.intellispaces.ixora.http.HttpMethods;
import tech.intellispaces.ixora.http.HttpPortExchangeChannel;
import tech.intellispaces.ixora.http.HttpRequestHandle;
import tech.intellispaces.ixora.http.HttpRequests;
import tech.intellispaces.ixora.http.UnmovableHttpResponseHandle;
import tech.intellispaces.jaquarius.space.channel.ChannelFunctions;

import java.io.IOException;

import static tech.intellispaces.commons.collection.CollectionFunctions.toList;

class JettyServlet extends HttpServlet {
  private MovableJettyServerPortHandle port;
  private String httpPortExchangeChannelCid;

  void init(MovableJettyServerPortHandle port) {
    this.port = port;
    this.httpPortExchangeChannelCid = ChannelFunctions.getChannelId(HttpPortExchangeChannel.class);
  }

  @Override
  protected void doGet(
      HttpServletRequest servletRequest, HttpServletResponse servletResponse
  ) throws IOException {
    HttpRequestHandle req = requestHandle(servletRequest);
    UnmovableHttpResponseHandle res = port.mapOfMovingThru(httpPortExchangeChannelCid, req);
    populateResponse(servletResponse, res);
  }

  private HttpRequestHandle requestHandle(HttpServletRequest req) {
    String url = req.getRequestURL().toString();
    String query = req.getQueryString();
    String uri = (query == null ? url : url + '?' + query);
    return HttpRequests.create(HttpMethods.get(req.getMethod()), uri);
  }

  private void populateResponse(
      HttpServletResponse servletResponse, UnmovableHttpResponseHandle responseHandle
  ) throws IOException {
    if (responseHandle.status().isOkStatus()) {
      servletResponse.setStatus(HttpServletResponse.SC_OK);
    } else {
      throw new RuntimeException();
    }

    byte[] body = ArraysFunctions.toByteArray(toList(responseHandle.bodyStream().readAll().iterator()));
    servletResponse.getOutputStream().write(body);
  }
}
