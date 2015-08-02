package com.king.game.service.impl;

import com.king.game.service.LoginService;
import com.king.game.service.SessionService;
import com.king.model.Session;

/**
 * Created by freyliis
 */
public class LoginServiceImpl implements LoginService {
    private SessionService sessionService;

    public LoginServiceImpl(final SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @Override
    public String login(final Integer userId) {
        Session session = sessionService.createSession(userId);
        return session.getSessionId();
    }
}
