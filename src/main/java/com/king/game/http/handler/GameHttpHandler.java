package com.king.game.http.handler;

import com.king.game.http.handler.factory.HandlerFactory;
import com.king.game.http.parser.Parser;
import com.king.game.service.GameContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

public class GameHttpHandler implements HttpHandler {

    private final GameContext gameContext;
    private final Parser parser;
    private final HandlerFactory handleFactory;

    public GameHttpHandler(final GameContext gameContext, final Parser parser, final HandlerFactory handleFactory) {
        this.gameContext = gameContext;
        this.parser = parser;
        this.handleFactory = handleFactory;
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        final RequestHandler requestHandler = handleFactory.getRequestHandler(httpExchange, parser, gameContext);
        final Response response = requestHandler.handleRequest(httpExchange);
        requestHandler.handleResponse(httpExchange, response);
    }


}
