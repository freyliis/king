package com.king.game.service.impl;

import com.king.game.service.TimeService;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;

public class TimeServiceImplTest {

    TimeService objectUnderTest = new TimeServiceImpl();
    @Test
    public void shouldRetrun1NanoDifferenceWhenIncreasing(){
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime after1Nano = now.plusNanos(1);
        final long difference = objectUnderTest.calculateAbsDifferenceInNano(now, after1Nano);
        Assert.assertThat(difference, CoreMatchers.is(1l));
    }

    @Test
    public void shouldRetrun1NanoDifferenceWhenDecreasing(){
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime after1Nano = now.minusNanos(1);
        final long difference = objectUnderTest.calculateAbsDifferenceInNano(now, after1Nano);
        Assert.assertThat(difference, CoreMatchers.is(1l));
    }

}