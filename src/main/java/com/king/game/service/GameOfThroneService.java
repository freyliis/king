package com.king.game.service;

import com.king.model.Score;

import java.util.Set;

public interface GameOfThroneService {

    Set<Score> getHighscoreList(int level);

    void postUserScore(String sessionId, Integer levelId, Integer score);

    String login(Integer userId);


}
