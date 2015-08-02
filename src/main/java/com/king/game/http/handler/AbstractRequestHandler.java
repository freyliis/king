package com.king.game.http.handler;

import com.king.game.http.RequestMethod;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by freyliis
 */
public abstract class AbstractRequestHandler implements RequestHandler {
    @Override
    public Response handleRequest(HttpExchange httpExchange) {
        if (validateRequestMethod(httpExchange, getValidRequestMethod())) {
            return handleSingleRequest(httpExchange);
        } else {
            return new Response("Wrong request method", RequestHandler.HTTP_FAILED_STATUS);
        }
    }

    protected boolean validateRequestMethod(HttpExchange httpExchange, RequestMethod requestMethod) {
        return httpExchange.getRequestMethod().equals(requestMethod.toString());
    }

    public abstract RequestMethod getValidRequestMethod();

    public abstract Response handleSingleRequest(HttpExchange httpExchange);

    @Override
    public void handleResponse(HttpExchange httpExchange, Response response) {
        String responseMessage = response.getResponse();
        try (OutputStream os = httpExchange.getResponseBody()) {
            httpExchange.sendResponseHeaders(response.getStatus(), responseMessage.getBytes().length);
            os.write(responseMessage.getBytes());
        } catch (IOException e) {
            try {
                httpExchange.sendResponseHeaders(HTTP_FAILED_STATUS, 0);
            } catch (IOException e1) {
                throw new RuntimeException(e1);
            }
        }
    }
}
