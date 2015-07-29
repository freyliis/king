package com.king.game.thread.threads;

import com.king.game.service.GameOfThroneService;

public class PostUserScoreThread implements Runnable {

    private Integer score;
    private Integer levelId;
    private String sessionId;
    private GameOfThroneService gameOfThroneService;

    public PostUserScoreThread(Integer score, Integer levelId, String sessionId, GameOfThroneService gameOfThroneService) {
        this.score = score;
        this.levelId = levelId;
        this.sessionId = sessionId;
        this.gameOfThroneService = gameOfThroneService;
    }

    @Override
    public void run() {
        gameOfThroneService.postUserScore(sessionId,levelId, score);
    }
}
