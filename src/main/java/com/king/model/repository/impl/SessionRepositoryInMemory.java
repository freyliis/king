package com.king.model.repository.impl;

import com.king.model.Session;
import com.king.model.repository.SessionRepository;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;


public class SessionRepositoryInMemory implements SessionRepository{

    private final ConcurrentMap<String, Session> sessionMap;

    public SessionRepositoryInMemory() {
        sessionMap = new ConcurrentHashMap<>();
    }

    public void saveSession(Session session) {
        sessionMap.put(session.getSessionId(), session);
    }

    public Session getSession(String sessionId) {
        return sessionMap.get(sessionId);
    }
}
