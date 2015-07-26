package com.king.game.service;

import com.king.model.HighScoreList;

public interface GameOfThroneService {

    HighScoreList getHighscoreList(int level);

    void postUserScore(String sessionId, Integer levelId, Integer score);

    String login(Integer userId);


}
