package com.king.game.http.parser.impl;

import com.king.game.http.parser.PostScoreRequestParser;
import com.sun.net.httpserver.HttpExchange;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by freyliis
 */
public class PostScoreRequestParserImpl implements PostScoreRequestParser {

    private final  ParserHandler parserHandler;

    public PostScoreRequestParserImpl(final ParserHandler parserHandler) {
        this.parserHandler = parserHandler;
    }

    ///<levelid>/score?sessionkey=<sessionkey>
    @Override
    public String parsePostScoreRequestForLevelId(String path) {
        return parserHandler.getFirstUrlPart(path);
    }
    //sessionkey=<sessionkey>
    @Override
    public String parsePostScoreRequestForSessionKey(String path) {
        String[] pathSplitted = path.split(parserHandler.PARAMETERS_DELIMITER);
        return pathSplitted[1];
    }
    @Override
    public String parsePostScoreRequestBody(HttpExchange httpExchange) {
        InputStream in = httpExchange.getRequestBody();
        String score = "";
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            byte buf[] = new byte[32];
            for (int n = in.read(buf); n > 0; n = in.read(buf)) {
                out.write(buf, 0, n);
            }
            score = new String(out.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
            return e.getLocalizedMessage();
        }
        return score;
    }

}
