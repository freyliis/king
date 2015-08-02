package com.king.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Session {

    private final UUID sessionId;
    private final LocalDateTime sessionStartTime;
    private final Integer userId;

    public Session(Integer userId) {
        this.sessionId = UUID.randomUUID();
        this.userId = userId;
        sessionStartTime = LocalDateTime.now();
    }


    public String getSessionId() {
        return sessionId.toString();
    }

    public LocalDateTime getSessionStartTime() {
        return sessionStartTime;
    }

    public Integer getUserId() {
        return userId;
    }
}
