package com.king.game.http.parser;

import com.sun.net.httpserver.HttpExchange;

/**
 * Created by freyliis
 */
public interface PostScoreRequestParser {
    String parsePostScoreRequestForLevelId(String path);
    String parsePostScoreRequestForSessionKey(String path);
    String parsePostScoreRequestBody(HttpExchange httpExchange);
}
