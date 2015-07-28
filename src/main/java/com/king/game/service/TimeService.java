package com.king.game.service;

import java.time.LocalDateTime;

public interface TimeService {

    long calculateAbsDifferenceInNano(LocalDateTime start, LocalDateTime end);
    long convertMinutesToNanos(long minutes);
}
