package com.king.game;

import com.king.game.http.GameHttpHandler;
import com.king.game.http.GameHttpServer;
import com.king.game.http.GetRequestParser;
import com.king.game.http.PostRequestParser;
import com.king.game.service.GameOfThroneService;
import com.king.game.service.impl.GameOfThroneServiceImpl;
import com.king.game.thread.ThreadPoolService;
import com.king.game.thread.impl.ThreadPoolServiceImpl;

public class Main {

    private static final String CONTEXT = "/";
    private static final int PORT = 8082;

    public static void main(String[] args) {

        GameOfThroneService gameOfThroneService = new GameOfThroneServiceImpl();
        ThreadPoolService threadPoolService = new ThreadPoolServiceImpl(gameOfThroneService);
        PostRequestParser postRequestParser = new PostRequestParser();
        GetRequestParser getRequestParser = new GetRequestParser();
        GameHttpServer gameHttpServer = new GameHttpServer(PORT, CONTEXT, new GameHttpHandler(threadPoolService, postRequestParser, getRequestParser));
        gameHttpServer.start();
    }
}
