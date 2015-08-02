package com.king.game.service.impl;

import com.king.game.service.TimeService;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

public class TimeServiceImpl implements TimeService {

    private final long timelapse;
    private final TimeUnit timeUnit;

    public TimeServiceImpl(final long timelapse, final TimeUnit timeUnit) {
        this.timelapse = timelapse;
        this.timeUnit = timeUnit;
    }

    public boolean isTimelapseTooHigh(LocalDateTime start, LocalDateTime end  ) {
        long sessionValidTimeInNanos = convertTimeToNanos(timelapse, timeUnit);
        final long nanos = calculateAbsDifferenceInNano(start, end);
        if(nanos > sessionValidTimeInNanos ) {
            return true;
        } else {
            return false;
        }
    }

    private long calculateAbsDifferenceInNano(LocalDateTime start, LocalDateTime end) {
        final long nanos = Duration.between(start, end).toNanos();
        return Math.abs(nanos);
    }

    private long convertTimeToNanos(long time, TimeUnit timeUnit) {
        return TimeUnit.NANOSECONDS.convert(time, timeUnit);
    }
}
