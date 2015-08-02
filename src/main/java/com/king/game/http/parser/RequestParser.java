package com.king.game.http.parser;

import com.king.game.http.RequestInfo;

/**
 * Created by freyliis
 */
public interface RequestParser {

    RequestInfo parseRequest(String path);
}
