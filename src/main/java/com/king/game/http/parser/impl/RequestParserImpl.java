package com.king.game.http.parser.impl;

import com.king.game.http.RequestInfo;
import com.king.game.http.parser.RequestParser;

/**
 * Created by freyliis
 */
public class RequestParserImpl implements RequestParser{

    private final ParserHandler parserHandler;

    public RequestParserImpl(final ParserHandler parserHandler) {
        this.parserHandler = parserHandler;
    }

    @Override
    public RequestInfo parseRequest(String path) {
        String[] pathSplitted = parserHandler.removeContextFromPath(path).split(parserHandler.URI_DELIMITER );
        return RequestInfo.getRequestInfo(pathSplitted[1]);
    }

}
