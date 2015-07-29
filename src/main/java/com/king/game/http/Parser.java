package com.king.game.http;

/**
 * Created by freyliis
 */
public class Parser {

    public String getFirstUrlPart(String path) {
        String[] pathSplitted = path.split(GameHttpHandler.URI_DELIMITER);
        return pathSplitted[1];
    }
}
