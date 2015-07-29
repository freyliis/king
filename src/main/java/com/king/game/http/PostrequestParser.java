package com.king.game.http;

import com.sun.net.httpserver.HttpExchange;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by freyliis
 */
public class PostRequestParser extends Parser{

    ///<levelid>/score?sessionkey=<sessionkey>
    public String parsePostScoreRequestForLevelId(String path) {
        return getFirstUrlPart(path);
    }
    //sessionkey=<sessionkey>
    public String parsePostScoreRequestForSessionKey(String path) {
        String[] pathSplitted = path.split(GameHttpHandler.PARAMETERS_DELIMITER);
        return pathSplitted[1];
    }

    public Integer parsePostScoreRequestBody(HttpExchange httpExchange) {
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
        }
        return Integer.parseInt(score);
    }

}
