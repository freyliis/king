package com.king.game.http;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.net.URI;

public class GameHttpHandler implements HttpHandler {

    public static final String URI_DELIMITER = "/";
    public static final String PARAMETERS_DELIMITER = "=";

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        String url = httpExchange.getRequestURI().getPath();
        final String requestMethod = httpExchange.getRequestMethod();
        if (requestMethod.equalsIgnoreCase("POST")) {
//            httpExchange.getRequestURI().getQuery();
        } else if (requestMethod.equalsIgnoreCase("GET")) {

        } else {

        }
    }
///<levelid>/score?sessionkey=<sessionkey>
    public String parsePostScoreMessageForLevelId(String path) {
        String[] pathSplitted = path.split(URI_DELIMITER);
        return pathSplitted[0];
    }
//sessionkey=<sessionkey>
    public String parsePostScoreMessageForSessionKey(String path) {
        String[] pathSplitted = path.split(PARAMETERS_DELIMITER);
        return pathSplitted[1];
    }


    public String parseLoginUserMessage(String path) {
        String[] pathSplitted = path.split(URI_DELIMITER);
        return pathSplitted[0];
    }


    public MessageInfo checkGetMessage(String path) {
        String[] pathSplitted = path.split(URI_DELIMITER);
        return MessageInfo.getMessageInfo(pathSplitted[1]);
    }
}
