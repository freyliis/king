package com.king.model;

import java.util.UUID;

public class Session {

    private UUID sessionId;

    public Session() {
        this.sessionId = UUID.randomUUID();
    }
}
