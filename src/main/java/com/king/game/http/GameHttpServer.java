package com.king.game.http;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GameHttpServer {

    private static final int DEFAULT_SOCKET_BACKLOG = 0;
    private HttpServer httpServer;
    private ExecutorService requestThreadPool = Executors.newCachedThreadPool();

    public GameHttpServer(int port, String context, HttpHandler handler) {
        try {
            httpServer = HttpServer.create(new InetSocketAddress(port), DEFAULT_SOCKET_BACKLOG);
            httpServer.createContext(context, handler);
            httpServer.setExecutor(requestThreadPool);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        this.httpServer.start();
    }
}
