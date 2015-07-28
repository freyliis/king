package com.king.model.repository.impl;

import com.king.model.Session;
import com.king.model.repository.SessionRepository;

import java.util.HashMap;
import java.util.Map;


public class SessionRepositoryInMemory implements SessionRepository{

    private Map<String, Session> sessionMap;

    public SessionRepositoryInMemory() {
        sessionMap = new HashMap<String, Session>();
    }

    public void saveSession(Session session) {
        sessionMap.put(session.getSessionId(), session);
    }

    public Session getSession(String sessionId) {
        return sessionMap.get(sessionId);
    }
}
