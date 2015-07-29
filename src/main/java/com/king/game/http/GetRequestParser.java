package com.king.game.http;

/**
 * Created by freyliis
 */
public class GetRequestParser extends Parser{

    public String parseLoginUserRequest(String path) {
        return getFirstUrlPart(path);
    }

    public String parseHighScoreListRequest(String path) {
        return getFirstUrlPart(path);
    }

    public GetRequestInfo checkGetRequest(String path) {
        String[] pathSplitted = path.split(GameHttpHandler.URI_DELIMITER);
        return GetRequestInfo.getRequestInfo(pathSplitted[1]);
    }

}
