package com.king.game.thread;

import com.king.game.service.GameOfThroneService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolService {

    private ExecutorService threadPool = Executors.newCachedThreadPool();
    private GameOfThroneService gameOfThroneService;

    public ThreadPoolService(GameOfThroneService gameOfThroneService) {
        this.gameOfThroneService = gameOfThroneService;
    }

    public void createAndRunPostUserScoreThread(Integer score, Integer levelId, String sessionId)
    {
        PostUserScoreThread postUserScoreThread = new PostUserScoreThread(score, levelId, sessionId, gameOfThroneService);
        threadPool.submit(postUserScoreThread);
    }
}
