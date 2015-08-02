package com.king.game.http.handler;

import com.king.game.http.RequestMethod;
import com.sun.net.httpserver.HttpExchange;

/**
 * Created by freyliis
 */
public class NotValidRequestHandler extends AbstractRequestHandler {
    private String message="The request url you provided is not valid";

    @Override
    public Response handleSingleRequest(HttpExchange httpExchange) {
        return new Response(message, RequestHandler.HTTP_FAILED_STATUS);
    }

    @Override
    public RequestMethod getValidRequestMethod() {
        return RequestMethod.GET;
    }


}
