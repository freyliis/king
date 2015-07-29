package com.king.game.thread.threads;

import com.king.game.service.GameOfThroneService;

import java.util.concurrent.Callable;

/**
 * Created by freyliis
 */
public class UserLoginThread implements Callable<String> {

    private Integer userId;
    private GameOfThroneService gameOfThroneService;

    public UserLoginThread(Integer userId, GameOfThroneService gameOfThroneService) {
        this.userId = userId;
        this.gameOfThroneService = gameOfThroneService;
    }

    @Override
    public String call() throws Exception {
        final String sessionId = gameOfThroneService.login(userId);
        return sessionId;
    }

}
