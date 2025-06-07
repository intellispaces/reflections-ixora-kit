package tech.intellispaces.ixora.jetty;

import java.io.IOException;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import tech.intellispaces.commons.collection.ArraysFunctions;
import tech.intellispaces.core.Rid;
import tech.intellispaces.ixora.http.HttpMethods;
import tech.intellispaces.ixora.http.HttpPortExchangeChannel;
import tech.intellispaces.ixora.http.HttpRequest;
import tech.intellispaces.ixora.http.HttpRequests;
import tech.intellispaces.ixora.http.HttpResponse;
import tech.intellispaces.reflections.framework.space.channel.ChannelFunctions;

import static tech.intellispaces.commons.collection.CollectionFunctions.toList;

class JettyServlet extends HttpServlet {
  private MovableJettyServerPort port;
  private Rid httpPortExchangeChannelCid;

  void init(MovableJettyServerPort port) {
    this.port = port;
    this.httpPortExchangeChannelCid = ChannelFunctions.getChannelId(HttpPortExchangeChannel.class);
  }

  @Override
  protected void doGet(
      HttpServletRequest servletRequest, HttpServletResponse servletResponse
  ) throws IOException {
    HttpRequest req = requestReflection(servletRequest);
    HttpResponse res = port.mapOfMovingThru(httpPortExchangeChannelCid, req);
    populateResponse(servletResponse, res);
  }

  private HttpRequest requestReflection(HttpServletRequest req) {
    String url = req.getRequestURL().toString();
    String query = req.getQueryString();
    String uri = (query == null ? url : url + '?' + query);
    return HttpRequests.create(HttpMethods.get(req.getMethod()), uri);
  }

  private void populateResponse(
      HttpServletResponse servletResponse, HttpResponse responseReflection
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
