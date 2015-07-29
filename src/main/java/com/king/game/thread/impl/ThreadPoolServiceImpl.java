package com.king.game.thread.impl;

import com.king.game.service.GameOfThroneService;
import com.king.game.thread.ThreadPoolService;
import com.king.game.thread.threads.HighScoreListThread;
import com.king.game.thread.threads.PostUserScoreThread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadPoolServiceImpl implements ThreadPoolService {

    private ExecutorService threadPool = Executors.newCachedThreadPool();
    private GameOfThroneService gameOfThroneService;

    public ThreadPoolServiceImpl(GameOfThroneService gameOfThroneService) {
        this.gameOfThroneService = gameOfThroneService;
    }

    public void createAndRunPostUserScoreThread(Integer score, Integer levelId, String sessionId)
    {
        PostUserScoreThread postUserScoreThread = new PostUserScoreThread(score, levelId, sessionId, gameOfThroneService);
        threadPool.submit(postUserScoreThread);
    }

    @Override
    public String createAndRunUserLoginThread(String userId) {
        return null;
    }

    @Override
    public String createAndRunHighScoreListThread(Integer levelId) {
        HighScoreListThread highScoreListThread = new HighScoreListThread(levelId, gameOfThroneService);
        final Future<String> highScorelist = threadPool.submit(highScoreListThread);
        try {
            return highScorelist.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

}
