package com.king.game.http.parser;

import com.king.game.http.RequestInfo;
import com.sun.net.httpserver.HttpExchange;

/**
 * Created by freyliis
 */
public interface Parser {

    String parsePostScoreRequestForLevelId(String request);

    String parsePostScoreRequestForSessionKey(String request);

    Integer parsePostScoreRequestBody(HttpExchange httpExchange);

    RequestInfo parseRequest(String path);

    String parseHighScoreListRequest(String path);

    String parseLoginUserRequest(String path);
}
