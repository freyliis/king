package com.king.game.service;

import com.king.model.Session;

public interface SessionService {

    boolean isSessionKeyActive(String sessionKey);

    Session createSession(Integer userId);

    Session getSession(String sessionKey);

}
