package com.king.game.service.impl;

import com.king.game.service.TimeService;
import com.king.model.Session;
import com.king.model.repository.SessionRepository;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDateTime;

import static org.mockito.Mockito.*;

/**
 * Created by freyliis
 */
@RunWith(MockitoJUnitRunner.class)
public class SessionServiceImplTest {

    @Mock
    TimeService timeService;
    @Mock
    Session session;
    @Mock
    SessionRepository sessionRepository;

    SessionServiceImpl objectUnderTest;

    @Before
    public void setUp() {
        objectUnderTest = new SessionServiceImpl(sessionRepository, timeService);
    }

    @Test
    public void shouldReturnTrueIfSessionIsActive() {
        when(sessionRepository.getSession(anyString())).thenReturn(session);
        when(session.getSessionStartTime()).thenReturn(LocalDateTime.now());
        when(timeService.isTimelapseTooHigh(any(LocalDateTime.class), any(LocalDateTime.class))).thenReturn(false);
        final boolean isActive = objectUnderTest.isSessionKeyActive("key");
        Assert.assertThat(isActive, CoreMatchers.is(Boolean.TRUE));
    }

    @Test
    public void shouldReturnFalseIfSessionIsNotActive() {
        when(sessionRepository.getSession(anyString())).thenReturn(session);
        when(session.getSessionStartTime()).thenReturn(LocalDateTime.now());
        when(timeService.isTimelapseTooHigh(any(LocalDateTime.class), any(LocalDateTime.class))).thenReturn(true);
        final boolean isActive = objectUnderTest.isSessionKeyActive("key");
        Assert.assertThat(isActive, CoreMatchers.is(Boolean.FALSE));
    }

}