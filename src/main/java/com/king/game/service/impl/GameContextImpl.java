package com.king.game.service.impl;

import com.king.game.service.GameContext;
import com.king.game.service.LoginService;
import com.king.game.service.ScoreService;

/**
 * Created by freyliis
 */
public class GameContextImpl implements GameContext {

    private final ScoreService scoreService;
    private final LoginService loginService;

    public GameContextImpl(final LoginService loginService, final ScoreService scoreService) {
        this.loginService = loginService;
        this.scoreService = scoreService;
    }


    @Override
    public LoginService getLoginService() {
        return loginService;
    }

    @Override
    public ScoreService getScoreService() {
        return scoreService;
    }
}
