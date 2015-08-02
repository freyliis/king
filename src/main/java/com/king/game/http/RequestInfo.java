package com.king.game.http;

public enum RequestInfo {

    LOGIN("login"), HIGHSCORE("highscorelist"), EMPTY("");

    private String uriMessage;

    RequestInfo(String uriMessage) {
        this.uriMessage = uriMessage;
    }

    public String getUriMessage() {
        return uriMessage;
    }

    public static RequestInfo getRequestInfo(String value) {
        for (RequestInfo info : values()) {
            if (info.getUriMessage().equalsIgnoreCase(value)) {
                return info;
            }
        }
        return EMPTY;
    }
}
