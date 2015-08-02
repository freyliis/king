package com.king.game.http.handler.factory;

import com.king.game.http.handler.RequestHandler;
import com.sun.net.httpserver.HttpExchange;

/**
 * Created by freyliis
 */
public interface HandlerFactory {

    RequestHandler getRequestHandler(HttpExchange httpExchange);
}
