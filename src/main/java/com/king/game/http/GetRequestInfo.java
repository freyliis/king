package com.king.game.http;

public enum GetRequestInfo {

    LOGIN("login"), HIGHSCORE("highscorelist"), EMPTY("");

    private String uriMessage;

    GetRequestInfo(String uriMessage) {
        this.uriMessage = uriMessage;
    }

    public String getUriMessage() {
        return uriMessage;
    }

    public static GetRequestInfo getRequestInfo(String value) {
        for (GetRequestInfo info : values()) {
            if (info.getUriMessage().equalsIgnoreCase(value)) {
                return info;
            }
        }
        return EMPTY;
    }
}
