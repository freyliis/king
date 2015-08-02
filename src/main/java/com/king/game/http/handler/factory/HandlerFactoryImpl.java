package com.king.game.http.handler.factory;

import com.king.game.http.RequestInfo;
import com.king.game.http.handler.*;
import com.king.game.http.parser.Parser;
import com.king.game.service.GameContext;
import com.sun.net.httpserver.HttpExchange;

/**
 * Created by freyliis
 */
public class HandlerFactoryImpl implements HandlerFactory {

    @Override
    public RequestHandler getRequestHandler(HttpExchange httpExchange, Parser parser, GameContext gameContext) {
        final RequestInfo requestInfo = parser.parseRequest(httpExchange.getRequestURI().getPath());
        switch (requestInfo) {
            case LOGIN:
                return new GetLoginRequestHandler(gameContext, parser);
            case HIGHSCORE:
                return new GetHighscoreListRequestHandler(gameContext, parser);
            case POST_SCORE:
                return new PostScoreRequestHandler(gameContext, parser);
            default:
                return new NotValidRequestHandler();
        }
    }
}
