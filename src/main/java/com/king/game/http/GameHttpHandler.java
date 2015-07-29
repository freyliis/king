package com.king.game.http;

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
    private PostRequestParser postRequestParser;
    private GetRequestParser getRequestParser;

    public GameHttpHandler(ThreadPoolService threadPoolService, PostRequestParser postRequestParser, GetRequestParser getRequestParser) {
        this.threadPoolService = threadPoolService;
        this.postRequestParser = postRequestParser;
        this.getRequestParser = getRequestParser;
    }

    public void handle(HttpExchange httpExchange) throws IOException {

        final String requestMethod = httpExchange.getRequestMethod();
        if (requestMethod.equalsIgnoreCase(POST)) {
            handlePostRequest(httpExchange);
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
        String levelId = postRequestParser.parsePostScoreRequestForLevelId(httpExchange.getRequestURI().getPath());
        String sessionId = postRequestParser.parsePostScoreRequestForSessionKey(httpExchange.getRequestURI().getQuery());
        Integer score = postRequestParser.parsePostScoreRequestBody(httpExchange);
        threadPoolService.createAndRunPostUserScoreThread(score, Integer.parseInt(levelId), sessionId);
    }



    private String handleGetRequest(HttpExchange httpExchange) {
        switch (getRequestParser.checkGetRequest(httpExchange.getRequestURI().getPath())) {
            case LOGIN:
               return handleLoginRequest(httpExchange);
            case HIGHSCORE:
                return handleHighScoreListRequest(httpExchange);
            default:
                return GetRequestInfo.EMPTY.getUriMessage();
        }
    }

    private String handleHighScoreListRequest(HttpExchange httpExchange) {
        final String levelId = getRequestParser.parseHighScoreListRequest(httpExchange.getRequestURI().getPath());
        final String highScoreResult = threadPoolService.createAndRunHighScoreListThread(Integer.parseInt(levelId));
        return highScoreResult;
    }

    private String handleLoginRequest(HttpExchange httpExchange) {
        final String userId = getRequestParser.parseLoginUserRequest(httpExchange.getRequestURI().getPath());
        final String sessionKey = threadPoolService.createAndRunUserLoginThread(Integer.parseInt(userId));
        return sessionKey;
    }



}
