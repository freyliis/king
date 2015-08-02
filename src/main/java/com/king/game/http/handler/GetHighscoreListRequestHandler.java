package com.king.game.http.handler;

import com.king.game.http.RequestMethod;
import com.king.game.http.parser.Parser;
import com.king.game.service.GameContext;
import com.king.model.Score;
import com.sun.net.httpserver.HttpExchange;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by freyliis
 */
public class GetHighscoreListRequestHandler extends AbstractRequestHandler {

    private GameContext gameContext;
    private Parser parser;
    private RequestMethod validRequestMethod;

    public GetHighscoreListRequestHandler(GameContext gameContext, Parser parser) {
        this.gameContext = gameContext;
        this.parser = parser;
        this.validRequestMethod = RequestMethod.GET;
    }

    @Override
    public RequestMethod getValidRequestMethod() {
        return validRequestMethod;
    }

    @Override
    public Response handleSingleRequest(HttpExchange httpExchange) {
        final String levelId = parser.parseHighScoreListRequest(httpExchange.getRequestURI().getPath());
        System.out.format("Get request received with: %s", levelId).println();
        int parsedLevelId;
        try {
            parsedLevelId  = Integer.parseInt(levelId);
        } catch (NumberFormatException e) {
            Response response = new Response("Wrong userId", RequestHandler.HTTP_FAILED_STATUS);
            return response;
        }
        final List<Score> highScoreResult = gameContext.getScoreService().getHighscoreList(parsedLevelId);
        System.out.format("Response received: %s", highScoreResult).println();
        Response response = new Response(parseToCommaSeparatedString(highScoreResult), RequestHandler.HTTP_OK_STATUS);
        return response;
    }

    public String parseToCommaSeparatedString(List<Score> highscoreList) {
        return highscoreList.stream().map(i -> i.toString()).collect(Collectors.joining(","));
    }
}


