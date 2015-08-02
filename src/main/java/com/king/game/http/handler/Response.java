package com.king.game.http.handler;

/**
 * Created by freyliis
 */
public class Response {

    private final String response;
    private final int status;

    public Response(String response, int status) {
        this.response = response;
        this.status = status;
    }

    public String getResponse() {
        return response;
    }

    public int getStatus() {
        return status;
    }
}
