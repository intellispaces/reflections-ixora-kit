package tech.intellispaces.ixora.jetty;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tech.intellispaces.commons.collection.ArraysFunctions;
import tech.intellispaces.ixora.http.HttpMethods;
import tech.intellispaces.ixora.http.HttpPortExchangeChannel;
import tech.intellispaces.ixora.http.HttpRequest;
import tech.intellispaces.ixora.http.HttpRequests;
import tech.intellispaces.ixora.http.UnmovableHttpResponseHandle;
import tech.intellispaces.jaquarius.object.reference.MovableObjectHandle;

import java.io.IOException;

import static tech.intellispaces.commons.collection.CollectionFunctions.toList;

class JettyServlet extends HttpServlet {
  private MovableObjectHandle<?> logicalPort;
  private Class<? extends HttpPortExchangeChannel> exchangeChannel;

  void init(MovableObjectHandle<?> logicalPort, Class<? extends HttpPortExchangeChannel> exchangeChannel) {
    this.logicalPort = logicalPort;
    this.exchangeChannel = exchangeChannel;
  }

  @Override
  protected void doGet(
      HttpServletRequest servletRequest, HttpServletResponse servletResponse
  ) throws IOException {
    HttpRequest request = buildRequest(servletRequest);
    UnmovableHttpResponseHandle response = logicalPort.mapOfMovingThru(exchangeChannel, request);
    populateServletResponse(servletResponse, response);
  }

  private HttpRequest buildRequest(HttpServletRequest req) {
    String url = req.getRequestURL().toString();
    String query = req.getQueryString();
    String uri = (query == null ? url : url + '?' + query);
    return HttpRequests.create(HttpMethods.get(req.getMethod()), uri);
  }

  private void populateServletResponse(
      HttpServletResponse servletResponse, UnmovableHttpResponseHandle response
  ) throws IOException {
    if (response.status().isOkStatus()) {
      servletResponse.setStatus(HttpServletResponse.SC_OK);
    } else {
      throw new RuntimeException();
    }

    byte[] body = ArraysFunctions.toByteArray(toList(response.bodyStream().readAll().iterator()));
    servletResponse.getOutputStream().write(body);
  }
}
