package com.king.game.service;

import com.king.model.HighScore;

import java.util.List;

public interface GameOfThroneService {

    List<HighScore> getHighscoreList(int level);

    void postUserScore(String sessionId, Integer levelId, Integer score);

    String login(Integer userId);


}
