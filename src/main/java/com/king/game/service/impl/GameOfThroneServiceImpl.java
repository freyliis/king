package com.king.game.service.impl;

import com.king.game.service.GameOfThroneService;
import com.king.model.*;
import com.king.model.repository.LevelRepository;

/**
 * Created by freyliis
 */
public class GameOfThroneServiceImpl implements GameOfThroneService {

    private LevelRepository levelRepository;

    public HighScoreList getHighscoreList(int levelId) {
        Level level = levelRepository.getLevel(levelId);
        return level.getHighScoreList();
    }

    public void postUserScore(String sessionId, Integer levelId, Integer score) {

    }

    public String login(Integer userId) {
        Session session = new Session(userId);
        return session.getSessionId();
    }
}
