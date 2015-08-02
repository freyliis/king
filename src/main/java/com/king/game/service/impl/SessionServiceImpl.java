package com.king.game.service.impl;

import com.king.game.service.SessionService;
import com.king.game.service.TimeService;
import com.king.model.Session;
import com.king.model.repository.SessionRepository;

import java.time.LocalDateTime;

public class SessionServiceImpl implements SessionService {

    private SessionRepository sessionRepository;
    private TimeService timeService;

    public SessionServiceImpl(SessionRepository sessionRepository, TimeService timeService) {
        this.sessionRepository = sessionRepository;
        this.timeService = timeService;
    }

    public boolean isSessionKeyActive(String sessionKey) {
        final Session session = sessionRepository.getSession(sessionKey);
        return !timeService.isTimelapseTooHigh(LocalDateTime.now(), session.getSessionStartTime());
    }

    public Session createSession(Integer userId) {
        Session session = new Session(userId);
        sessionRepository.saveSession(session);
        return session;
    }

    public Session getSession(String sessionKey) {
        return sessionRepository.getSession(sessionKey);
    }
}
