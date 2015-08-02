package com.king.game.service;

import com.king.model.Score;

import java.util.List;

public interface ScoreService {

    List<Score> getHighscoreList(int level);

    String postUserScore(String sessionId, Integer levelId, Integer score);

}
