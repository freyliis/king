package com.king.game.http.handler.factory;

import com.king.game.http.RequestInfo;
import com.king.game.http.handler.*;
import com.king.game.http.parser.RequestParser;
import com.king.game.http.parser.impl.*;
import com.king.game.service.GameContext;
import com.sun.net.httpserver.HttpExchange;

/**
 * Created by freyliis
 */
public class HandlerFactoryImpl implements HandlerFactory {

    private final RequestParser requestParser;
    private final GameContext gameContext;
    private final ParserHandler parserHandler;

    public HandlerFactoryImpl(final ParserHandler parserHandler, final GameContext gameContext, final RequestParser requestParser) {
        this.parserHandler = parserHandler;
        this.requestParser =requestParser;
        this.gameContext = gameContext;
    }

    @Override
    public RequestHandler getRequestHandler(HttpExchange httpExchange) {
        final RequestInfo requestInfo = requestParser.parseRequest(httpExchange.getRequestURI().getPath());
        switch (requestInfo) {
            case LOGIN:
                return new GetLoginRequestHandler(gameContext, new LoginRequestParserImpl(parserHandler));
            case HIGHSCORE:
                return new GetHighscoreListRequestHandler(gameContext, new HighScoreRequestParserImpl(parserHandler) {
                });
            case POST_SCORE:
                return new PostScoreRequestHandler(gameContext, new PostScoreRequestParserImpl(parserHandler));
            default:
                return new NotValidRequestHandler();
        }
    }
}
