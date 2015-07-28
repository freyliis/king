package com.king.game.service.impl;


import com.king.game.service.SessionService;
import com.king.game.service.TimeService;
import com.king.model.Session;
import com.king.model.repository.SessionRepository;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.junit.Assert;

import static org.mockito.Mockito.*;

import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDateTime;


@RunWith(MockitoJUnitRunner.class)
public class SessionServiceImplTest {

    @Mock
    Session session;
    @Mock
    SessionRepository sessionRepository;
    @Mock
    TimeService timeService;

    SessionService objectUnderTest;

    @Before
    public void setUp() {
        objectUnderTest = new SessionServiceImpl(sessionRepository, timeService);
        when(sessionRepository.getSession(anyString())).thenReturn(session);
    }

    @Test
    public void shouldReturnTrueWhenSessionActiveForLessThan10Min() {
        LocalDateTime start = LocalDateTime.now().minusMinutes(10);
        when(session.getSessionStartTime()).thenReturn(start);
        boolean sessionKeyActive = objectUnderTest.isSessionKeyActive("id");
        Assert.assertThat(sessionKeyActive, CoreMatchers.is(Boolean.TRUE));
    }

    @Test
    public void shouldReturnTrueWhenSessionActiveForMoreThan10Min() {
        LocalDateTime start = LocalDateTime.now().minusMinutes(10).minusNanos(1);
        when(session.getSessionStartTime()).thenReturn(start);
        boolean sessionKeyActive = objectUnderTest.isSessionKeyActive("id");
        Assert.assertThat(sessionKeyActive, CoreMatchers.is(Boolean.FALSE));
    }

}