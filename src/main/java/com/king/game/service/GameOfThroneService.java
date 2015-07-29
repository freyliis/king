package com.king.game.service;

import com.king.model.ScoreList;

public interface GameOfThroneService {

    ScoreList getHighscoreList(int level);

    void postUserScore(String sessionId, Integer levelId, Integer score);

    String login(Integer userId);


}
