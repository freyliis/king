package com.king.game;

import com.king.game.http.GameHttpHandler;
import com.king.game.http.GameHttpServer;
import com.king.game.http.parser.Parser;
import com.king.game.http.parser.RequestParser;
import com.king.game.service.GameOfThroneService;
import com.king.game.service.SessionService;
import com.king.game.service.TimeService;
import com.king.game.service.impl.GameOfThroneServiceImpl;
import com.king.game.service.impl.SessionServiceImpl;
import com.king.game.service.impl.TimeServiceImpl;
import com.king.game.thread.ThreadPoolService;
import com.king.game.thread.impl.ThreadPoolServiceImpl;
import com.king.model.comparator.highscore.HighScoreComparator;
import com.king.model.comparator.highscore.impl.HighScoreReverseOrderComparator;
import com.king.model.repository.LevelRepository;
import com.king.model.repository.SessionRepository;
import com.king.model.repository.impl.LevelRepositoryInMemory;
import com.king.model.repository.impl.SessionRepositoryInMemory;

public class Main {

    private static final String CONTEXT = "/";
    private static final int PORT = 8082;
    public static final int SESSION_VALID_TIME_IN_MINUTES = 1;
    private static final int HIGH_SCORE_LIST_SIZE = 10;

    public static void main(String[] args) {
        System.out.format("Server runs with: %s, %d", CONTEXT, PORT).println();
        HighScoreComparator highScoreComparator = new HighScoreReverseOrderComparator();
        LevelRepository levelRepository = new LevelRepositoryInMemory(HIGH_SCORE_LIST_SIZE,highScoreComparator );
        SessionRepository sessionRepository = new SessionRepositoryInMemory();
        TimeService timeService = new TimeServiceImpl();
        SessionService sessionService = new SessionServiceImpl(sessionRepository, timeService, SESSION_VALID_TIME_IN_MINUTES);
        GameOfThroneService gameOfThroneService = new GameOfThroneServiceImpl(levelRepository, sessionService);
        ThreadPoolService threadPoolService = new ThreadPoolServiceImpl(gameOfThroneService);
        Parser getRequestParser = new RequestParser();
        GameHttpServer gameHttpServer = new GameHttpServer(PORT, CONTEXT, new GameHttpHandler(threadPoolService, getRequestParser));
        gameHttpServer.start();
    }
}
