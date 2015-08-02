package com.king.game.http.parser.impl;

/**
 * Created by freyliis
 */
public class ParserHandler {

    public static final String URI_DELIMITER = "/";
    public static final String PARAMETERS_DELIMITER = "=";
    private final String context;

    public ParserHandler(final String context) {
        this.context = context;
    }

    public String removeContextFromPath(String path) {
        if (path.startsWith(context)) {
            return path.substring(context.length());
        } else {
            return path;
        }
    }

    public String getFirstUrlPart(String path) {
        final String removeContextFromPath = removeContextFromPath(path);
        String[] pathSplitted = removeContextFromPath.split(URI_DELIMITER);
        return pathSplitted[0];
    }
}
