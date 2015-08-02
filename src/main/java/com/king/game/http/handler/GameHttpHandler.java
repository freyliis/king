package com.king.game.http.handler;

import com.king.game.http.handler.factory.HandlerFactory;
import com.king.game.service.GameContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

public class GameHttpHandler implements HttpHandler {

    private final GameContext gameContext;
    private final HandlerFactory handleFactory;

    public GameHttpHandler(final GameContext gameContext , final HandlerFactory handleFactory) {
        this.gameContext = gameContext;
        this.handleFactory = handleFactory;
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        final RequestHandler requestHandler = handleFactory.getRequestHandler(httpExchange);
        final Response response = requestHandler.handleRequest(httpExchange);
        requestHandler.handleResponse(httpExchange, response);
    }


}
