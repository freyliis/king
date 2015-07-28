package com.king.game.service.impl;

import com.king.game.service.SessionService;
import com.king.game.service.TimeService;
import com.king.model.Session;
import com.king.model.repository.SessionRepository;

import java.time.LocalDateTime;

public class SessionServiceImpl implements SessionService {

    private SessionRepository sessionRepository;
    private long sessionValidTimeInMinutes = 10l;
    private TimeService timeService;

    public SessionServiceImpl(SessionRepository sessionRepository, TimeService timeService) {
        this.sessionRepository = sessionRepository;
        this.timeService = timeService;
    }

    public boolean isSessionKeyActive(String sessionKey) {
        final Session session = sessionRepository.getSession(sessionKey);
        long sessionValidTimeInNanos = timeService.convertMinutesToNanos(sessionValidTimeInMinutes);
        final long nanos = timeService.calculateAbsDifferenceInNano(LocalDateTime.now(), session.getSessionStartTime());
        if(nanos > sessionValidTimeInNanos) {
            return false;
        } else {
            return true;
        }
    }

    public Session createSession(Integer userId) {
        return null;
    }
}
