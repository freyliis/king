package com.king.game.service.impl;

import com.king.game.service.TimeService;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

public class TimeServiceImpl implements TimeService {

    public long calculateAbsDifferenceInNano(LocalDateTime start, LocalDateTime end) {
        final long nanos = Duration.between(start, end).toNanos();
        return Math.abs(nanos);
    }

    public long convertMinutesToNanos(long minutes) {
        return TimeUnit.NANOSECONDS.convert(minutes, TimeUnit.MINUTES);
    }
}
