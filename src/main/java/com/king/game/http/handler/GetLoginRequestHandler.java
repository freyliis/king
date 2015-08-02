package com.king.game.http.handler;

import com.king.game.http.RequestMethod;
import com.king.game.http.parser.LoginRequestParser;
import com.king.game.service.GameContext;
import com.sun.net.httpserver.HttpExchange;

/**
 * Created by freyliis
 */
public class GetLoginRequestHandler extends AbstractRequestHandler {

    private GameContext gameContext;
    private LoginRequestParser parser;
    private RequestMethod validRequestMethod;

    public GetLoginRequestHandler(GameContext gameContext, LoginRequestParser parser) {
        this.gameContext = gameContext;
        this.parser = parser;
        this.validRequestMethod = RequestMethod.GET;
    }

    @Override
    public Response handleSingleRequest(HttpExchange httpExchange) {
        final String userId = parser.parseLoginUserRequest(httpExchange.getRequestURI().getPath());
        System.out.format("Get request received with: %s", userId).println();
        int parsedUserId;
        try {
            parsedUserId  = Integer.parseInt(userId);
        } catch (NumberFormatException e) {
            return new Response("Wrong userId", RequestHandler.HTTP_FAILED_STATUS);
        }
        final String sessionKey = gameContext.getLoginService().login(parsedUserId);
        System.out.format("Response received: %s", sessionKey).println();
        Response response = new Response(sessionKey, RequestHandler.HTTP_OK_STATUS);
        return response;
    }

    @Override
    public RequestMethod getValidRequestMethod() {
        return validRequestMethod;
    }
}
