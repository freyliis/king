package com.king.game.thread;

/**
 * Created by freyliis
 */
public interface ThreadPoolService {

    public void createAndRunPostUserScoreThread(Integer score, Integer levelId, String sessionId);

    public String createAndRunUserLoginThread(Integer userId);

    public String createAndRunHighScoreListThread(Integer levelId);
}
