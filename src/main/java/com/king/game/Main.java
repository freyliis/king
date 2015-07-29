package com.king.game;

import com.king.game.http.GameHttpHandler;
import com.king.game.http.GameHttpServer;

public class Main {

    private static final String CONTEXT = "/";
    private static final int PORT = 8082;

    public static void main(String[] args) {

        GameHttpServer gameHttpServer = new GameHttpServer(PORT, CONTEXT, new GameHttpHandler());
        gameHttpServer.start();
    }
}
