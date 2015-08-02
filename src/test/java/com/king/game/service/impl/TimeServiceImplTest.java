package com.king.game.service.impl;

import com.king.game.service.TimeService;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

public class TimeServiceImplTest {

    TimeService objectUnderTest;

    @Test
    public void shouldRetrunTrueWhenTimelapseIsTooHigh(){
        objectUnderTest = new TimeServiceImpl(1l, TimeUnit.NANOSECONDS);
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime after1Nano = now.plusNanos(2);
        final boolean timelapseTooHigh = objectUnderTest.isTimelapseTooHigh(now, after1Nano );
        Assert.assertThat(timelapseTooHigh, CoreMatchers.is(Boolean.TRUE));
    }

    @Test
    public void shouldRetrunFalseWhenTimelapseIsNotTooHigh(){
        objectUnderTest = new TimeServiceImpl(1l, TimeUnit.MINUTES);
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime after1Nano = now.plusNanos(1);
        final boolean timelapseTooHigh = objectUnderTest.isTimelapseTooHigh(now, after1Nano);
        Assert.assertThat(timelapseTooHigh, CoreMatchers.is(Boolean.FALSE));
    }

    @Test
    public void shouldRetrunFalseWhenTimelapseIsEqual(){
        objectUnderTest = new TimeServiceImpl(1l, TimeUnit.NANOSECONDS);
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime after1Nano = now.plusNanos(1);
        final boolean timelapseTooHigh = objectUnderTest.isTimelapseTooHigh(now, after1Nano);
        Assert.assertThat(timelapseTooHigh, CoreMatchers.is(Boolean.FALSE));
    }


}