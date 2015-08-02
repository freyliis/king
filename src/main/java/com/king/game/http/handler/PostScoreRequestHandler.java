package com.king.game.http.handler;

import com.king.game.http.RequestMethod;
import com.king.game.http.parser.PostScoreRequestParser;
import com.king.game.service.GameContext;
import com.sun.net.httpserver.HttpExchange;

/**
 * Created by freyliis
 */
public class PostScoreRequestHandler extends AbstractRequestHandler {
    private final RequestMethod validRequestMethod;
    private GameContext gameContext;
    private PostScoreRequestParser parser;

    public PostScoreRequestHandler(GameContext gameContext, PostScoreRequestParser parser) {
        this.gameContext = gameContext;
        this.parser = parser;
        this.validRequestMethod = RequestMethod.POST;
    }

    @Override
    public RequestMethod getValidRequestMethod() {
        return validRequestMethod;
    }

    @Override
    public Response handleSingleRequest(HttpExchange httpExchange) {
        String levelIdString = parser.parsePostScoreRequestForLevelId(httpExchange.getRequestURI().getPath());
        String sessionId = parser.parsePostScoreRequestForSessionKey(httpExchange.getRequestURI().getQuery());
        String scoreString = parser.parsePostScoreRequestBody(httpExchange);
        Integer score;
        Integer levelId;
        try {
            score = Integer.parseInt(scoreString);
            levelId = Integer.parseInt(levelIdString);
        } catch (NumberFormatException e) {
            Response response = new Response("Wrong score or levelId value", RequestHandler.HTTP_FAILED_STATUS);
            return response;
        }
        System.out.format("Post request received with: %s, %s, %d", levelId, sessionId, score).println();
        final String message = gameContext.getScoreService().postUserScore(sessionId, levelId, score);
        return new Response(message, RequestHandler.HTTP_OK_STATUS);
    }
}
