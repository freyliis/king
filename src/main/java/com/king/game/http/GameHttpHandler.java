package com.king.game.http;

import com.king.game.thread.ThreadPoolService;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

public class GameHttpHandler implements HttpHandler {

    public static final String URI_DELIMITER = "/";
    public static final String PARAMETERS_DELIMITER = "=";

    private ThreadPoolService threadPoolService;
    private PostRequestParser postRequestParser;
    private GetRequestParser getRequestParser;

    public GameHttpHandler(ThreadPoolService threadPoolService, PostRequestParser postRequestParser, GetRequestParser getRequestParser) {
        this.threadPoolService = threadPoolService;
        this.postRequestParser = postRequestParser;
        this.getRequestParser = getRequestParser;
    }

    public void handle(HttpExchange httpExchange) throws IOException {

        final String requestMethod = httpExchange.getRequestMethod();
        if (requestMethod.equalsIgnoreCase("POST")) {
            handlePostRequest(httpExchange);

        } else if (requestMethod.equalsIgnoreCase("GET")) {
            handleGetRequest(httpExchange);
        } else {

        }
    }

    private void handlePostRequest(HttpExchange httpExchange) {
        String levelId = postRequestParser.parsePostScoreRequestForLevelId(httpExchange.getRequestURI().getPath());
        String sessionId = postRequestParser.parsePostScoreRequestForSessionKey(httpExchange.getRequestURI().getQuery());
        Integer score = postRequestParser.parsePostScoreRequestBody(httpExchange);
        threadPoolService.createAndRunPostUserScoreThread(score, Integer.parseInt(levelId), sessionId);
    }



    private void handleGetRequest(HttpExchange httpExchange) {
        switch (getRequestParser.checkGetRequest(httpExchange.getRequestURI().getPath())) {
            case LOGIN:
                handleLoginRequest(httpExchange);
                break;
            case HIGHSCORE:
                handleHighScoreListRequest(httpExchange);
                break;
            case EMPTY:
                break;
        }

    }

    private void handleHighScoreListRequest(HttpExchange httpExchange) {
        final String levelId = getRequestParser.parseHighScoreListRequest(httpExchange.getRequestURI().getPath());

    }

    private void handleLoginRequest(HttpExchange httpExchange) {
        final String userId = getRequestParser.parseLoginUserRequest(httpExchange.getRequestURI().getPath());
    }

}
