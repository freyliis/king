package com.king.game;

import com.king.game.http.GameHttpServer;
import com.king.game.http.handler.GameHttpHandler;
import com.king.game.http.handler.factory.HandlerFactory;
import com.king.game.http.handler.factory.HandlerFactoryImpl;
import com.king.game.http.parser.Parser;
import com.king.game.http.parser.RequestWithoutContextParser;
import com.king.game.service.*;
import com.king.game.service.impl.*;
import com.king.model.comparator.highscore.HighScoreComparator;
import com.king.model.comparator.highscore.impl.HighScoreReverseOrderComparator;
import com.king.model.repository.LevelRepository;
import com.king.model.repository.SessionRepository;
import com.king.model.repository.impl.LevelRepositoryInMemory;
import com.king.model.repository.impl.SessionRepositoryInMemory;

import java.util.concurrent.TimeUnit;

public class Main {

    public static final int SESSION_VALID_TIME_IN_MINUTES = 10;
    private static final String CONTEXT = "/";
    private static final int PORT = 8082;
    private static final int HIGH_SCORE_LIST_SIZE = 15;

    public static void main(String[] args) {
        System.out.format("Server runs with: %s, %d", CONTEXT, PORT).println();
        HandlerFactory handlerFactory = new HandlerFactoryImpl();
        HighScoreComparator highScoreComparator = new HighScoreReverseOrderComparator();
        LevelRepository levelRepository = new LevelRepositoryInMemory(HIGH_SCORE_LIST_SIZE,highScoreComparator );
        SessionRepository sessionRepository = new SessionRepositoryInMemory();
        TimeService timeService = new TimeServiceImpl(SESSION_VALID_TIME_IN_MINUTES, TimeUnit.MINUTES);
        SessionService sessionService = new SessionServiceImpl(sessionRepository, timeService);
        ScoreService scoreService = new ScoreServiceImpl(levelRepository, sessionService);
        LoginService loginService = new LoginServiceImpl(sessionService);
        GameContext gameContext = new GameContextImpl(loginService, scoreService);
        Parser getRequestParser = new RequestWithoutContextParser(CONTEXT);
        GameHttpServer gameHttpServer = new GameHttpServer(PORT, CONTEXT, new GameHttpHandler(gameContext, getRequestParser, handlerFactory));
        gameHttpServer.start();
    }
}
