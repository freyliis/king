package com.king.game.http;

import com.king.game.http.parser.Parser;
import com.king.game.thread.ThreadPoolService;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

public class GameHttpHandler implements HttpHandler {

    public static final String URI_DELIMITER = "/";
    public static final String PARAMETERS_DELIMITER = "=";
    private static final int HTTP_OK_STATUS = 200;
    public static final String POST = "POST";
    public static final String GET = "GET";

    private ThreadPoolService threadPoolService;
    private Parser parser;

    public GameHttpHandler(ThreadPoolService threadPoolService, Parser parser) {
        this.threadPoolService = threadPoolService;
        this.parser = parser;
    }

    public void handle(HttpExchange httpExchange) throws IOException {
        final String requestMethod = httpExchange.getRequestMethod();
        if (requestMethod.equalsIgnoreCase(POST)) {
            handlePostRequest(httpExchange);
            handleResponse(httpExchange, "");
        } else if (requestMethod.equalsIgnoreCase(GET)) {
            final String response = handleGetRequest(httpExchange);
            handleResponse(httpExchange, response);
        }
    }

    private void handleResponse(HttpExchange httpExchange, String response) {
        try (OutputStream os = httpExchange.getResponseBody()){
            httpExchange.sendResponseHeaders(HTTP_OK_STATUS, response.getBytes().length);
            os.write(response.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handlePostRequest(HttpExchange httpExchange) {
        String levelId = parser.parsePostScoreRequestForLevelId(httpExchange.getRequestURI().getPath());
        String sessionId = parser.parsePostScoreRequestForSessionKey(httpExchange.getRequestURI().getQuery());
        Integer score = parser.parsePostScoreRequestBody(httpExchange);
        System.out.format("Post request received with: %s, %s, %d", levelId, sessionId, score).println();
        threadPoolService.createAndRunPostUserScoreThread(score, Integer.parseInt(levelId), sessionId);
    }



    private String handleGetRequest(HttpExchange httpExchange) {
        switch (parser.parseRequest(httpExchange.getRequestURI().getPath())) {
            case LOGIN:
               return handleLoginRequest(httpExchange);
            case HIGHSCORE:
                return handleHighScoreListRequest(httpExchange);
            default:
                return RequestInfo.EMPTY.getUriMessage();
        }
    }

    private String handleHighScoreListRequest(HttpExchange httpExchange) {
        final String levelId = parser.parseHighScoreListRequest(httpExchange.getRequestURI().getPath());
        System.out.format("Get request received with: %s", levelId).println();
        final String highScoreResult = threadPoolService.createAndRunHighScoreListThread(Integer.parseInt(levelId));
        System.out.format("Response received: %s", highScoreResult).println();
        return highScoreResult;
    }

    private String handleLoginRequest(HttpExchange httpExchange) {
        final String userId = parser.parseLoginUserRequest(httpExchange.getRequestURI().getPath());
        System.out.format("Get request received with: %s", userId).println();
        final String sessionKey = threadPoolService.createAndRunUserLoginThread(Integer.parseInt(userId));
        System.out.format("Response received: %s", sessionKey).println();
        return sessionKey;
    }



}
