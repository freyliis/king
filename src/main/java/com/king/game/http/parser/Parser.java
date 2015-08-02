package com.king.game.http.parser;

import com.king.game.http.RequestInfo;
import com.sun.net.httpserver.HttpExchange;

/**
 * Created by freyliis
 */
public interface Parser {

    public static final String URI_DELIMITER = "/";
    public static final String PARAMETERS_DELIMITER = "=";

    String parsePostScoreRequestForLevelId(String request);

    String parsePostScoreRequestForSessionKey(String request);

    String parsePostScoreRequestBody(HttpExchange httpExchange);

    RequestInfo parseRequest(String path);

    String parseHighScoreListRequest(String path);

    String parseLoginUserRequest(String path);
}
