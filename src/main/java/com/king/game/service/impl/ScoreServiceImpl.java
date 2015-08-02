package com.king.game.service.impl;

import com.king.game.service.ScoreService;
import com.king.game.service.SessionService;
import com.king.model.Level;
import com.king.model.Score;
import com.king.model.Session;
import com.king.model.repository.LevelRepository;

import java.util.List;

/**
 * Created by freyliis
 */
public class ScoreServiceImpl implements ScoreService {

    public static final String SCORE_POSTED_MESSGAGE = "Score posted";
    private LevelRepository levelRepository;
    private SessionService sessionService;

    public ScoreServiceImpl(LevelRepository levelRepository, SessionService sessionService) {
        this.levelRepository = levelRepository;
        this.sessionService = sessionService;
    }

    public List<Score> getHighscoreList(int levelId) {
        Level level = levelRepository.createOrGetLevel(levelId);
        return level.getHighScoreList();
    }

    public String postUserScore(String sessionId, Integer levelId, Integer score) {
        if(sessionService.isSessionKeyActive(sessionId)){
            final Session session = sessionService.getSession(sessionId);
            Level level = levelRepository.createOrGetLevel(levelId);
            level.postScore(score, session.getUserId());
            return SCORE_POSTED_MESSGAGE;
        } else {
            return String.format("Session %s is not active", sessionId);
        }
    }
}
