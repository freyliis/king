package com.king.game.http.parser.impl;

import com.king.game.http.parser.LoginRequestParser;

/**
 * Created by freyliis
 */
public class LoginRequestParserImpl  implements LoginRequestParser {

    private final  ParserHandler parserHandler;

    public LoginRequestParserImpl(final ParserHandler parserHandler) {
        this.parserHandler = parserHandler;
    }
    @Override
    public String parseLoginUserRequest(String path) {
        return parserHandler.getFirstUrlPart(path);
    }
}
