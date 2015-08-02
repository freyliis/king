package com.king.game.http.parser.impl;

import com.king.game.http.parser.HighScoreRequestParser;

/**
 * Created by freyliis
 */
public class HighScoreRequestParserImpl implements HighScoreRequestParser {

    private final  ParserHandler parserHandler;

    public HighScoreRequestParserImpl(final ParserHandler parserHandler) {
        this.parserHandler = parserHandler;
    }

    @Override
    public String parseHighScoreListRequest(String path) {
        final String pathWithoutContext = parserHandler.removeContextFromPath(path);
        return parserHandler.getFirstUrlPart(pathWithoutContext);
    }
}
