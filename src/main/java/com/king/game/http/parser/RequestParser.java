package com.king.game.http.parser;

import com.king.game.http.RequestInfo;
import com.sun.net.httpserver.HttpExchange;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by freyliis
 */
public class RequestParser {


    public String getFirstUrlPart(String path) {
        String[] pathSplitted = path.split(Parser.URI_DELIMITER);
        return pathSplitted[0];
    }

    public String parseLoginUserRequest(String path) {
        return getFirstUrlPart(path);
    }

    public String parseHighScoreListRequest(String path) {
        return getFirstUrlPart(path);
    }

    public RequestInfo parseRequest(String path) {
        String[] pathSplitted = path.split(Parser.URI_DELIMITER);
        return RequestInfo.getRequestInfo(pathSplitted[1]);
    }

    ///<levelid>/score?sessionkey=<sessionkey>
    public String parsePostScoreRequestForLevelId(String path) {
        return getFirstUrlPart(path);
    }
    //sessionkey=<sessionkey>
    public String parsePostScoreRequestForSessionKey(String path) {
        String[] pathSplitted = path.split(Parser.PARAMETERS_DELIMITER);
        return pathSplitted[1];
    }

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
