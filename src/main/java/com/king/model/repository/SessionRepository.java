package com.king.model.repository;

import com.king.model.Session;


public interface SessionRepository {

    void saveSession(Session session);

    Session getSession(String sessionId);

}
