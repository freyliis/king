package com.king.game.service.impl;

import com.king.game.service.GameOfThroneService;
import com.king.game.service.SessionService;
import com.king.model.*;
import com.king.model.repository.LevelRepository;
import com.king.model.repository.SessionRepository;

/**
 * Created by freyliis
 */
public class GameOfThroneServiceImpl implements GameOfThroneService {

    private LevelRepository levelRepository;
    private SessionService sessionService;

    public HighScoreList getHighscoreList(int levelId) {
        Level level = levelRepository.getLevel(levelId);
        return level.getHighScoreList();
    }

    public void postUserScore(String sessionId, Integer levelId, Integer score) {

    }

    public String login(Integer userId) {
        Session session = sessionService.createSession(userId);
        return session.getSessionId();
    }
}
