package com.king.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Session {

    private UUID sessionId;
    private LocalDateTime localDateTime;
    private Integer userId;

    public Session(Integer userId) {
        this.sessionId = UUID.randomUUID();
        this.userId = userId;
        localDateTime = LocalDateTime.now();
    }


    public String getSessionId() {
        return sessionId.toString();
    }
}
