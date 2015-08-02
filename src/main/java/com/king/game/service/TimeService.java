package com.king.game.service;

import java.time.LocalDateTime;

public interface TimeService {

    boolean isTimelapseTooHigh(LocalDateTime start, LocalDateTime end );
}
