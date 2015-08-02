package com.king.game.http.handler;

import com.sun.net.httpserver.HttpExchange;

/**
 * Created by freyliis
 */
public interface RequestHandler {

    public static final int HTTP_OK_STATUS = 200;
    public static final int HTTP_FAILED_STATUS = 500;

    Response handleRequest(HttpExchange httpExchange);
    void handleResponse(HttpExchange httpExchange, Response response);
}
