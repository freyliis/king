package com.king.game.http.parser;

import com.king.game.http.RequestInfo;
import com.sun.net.httpserver.HttpExchange;

/**
 * Created by freyliis
 */
public class RequestWithoutContextParser implements Parser {


    private final String context;
    private final RequestParser requestParser;

    public RequestWithoutContextParser(final String context) {
        this.context = context;
        requestParser = new RequestParser();
    }

    @Override
    public String parsePostScoreRequestForLevelId(final String request) {
        return requestParser.parsePostScoreRequestForLevelId(removeContextFromPath(request));
    }

    @Override
    public String parsePostScoreRequestForSessionKey(final String request) {
        return requestParser.parsePostScoreRequestForSessionKey(removeContextFromPath(request));
    }

    @Override
    public String parsePostScoreRequestBody(final HttpExchange httpExchange) {
        return requestParser.parsePostScoreRequestBody(httpExchange);
    }

    @Override
    public RequestInfo parseRequest(final String path) {
        return requestParser.parseRequest(removeContextFromPath(path));
    }

    @Override
    public String parseHighScoreListRequest(final String path) {
        return requestParser.parseHighScoreListRequest(removeContextFromPath(path));
    }

    @Override
    public String parseLoginUserRequest(final String path) {
        return requestParser.parseLoginUserRequest(removeContextFromPath(path));
    }

    protected String removeContextFromPath(String path) {
        if(path.startsWith(context)){
            return path.substring(context.length());
        }
        else {
            return path;
        }
    }
}
