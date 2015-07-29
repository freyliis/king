package com.king.game.http;

public enum MessageInfo {

    LOGIN("login"), HIGHSCORE("highscorelist"), EMPTY("");

    private String uriMessage;

    MessageInfo(String uriMessage) {
        this.uriMessage = uriMessage;
    }

    public String getUriMessage() {
        return uriMessage;
    }

    public static MessageInfo getMessageInfo(String value) {
        for (MessageInfo info : values()) {
            if (info.getUriMessage().equalsIgnoreCase(value)) {
                return info;
            }
        }
        return EMPTY;
    }
}
