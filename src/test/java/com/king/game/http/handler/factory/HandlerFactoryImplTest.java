package com.king.game.http.handler.factory;

import com.king.game.http.RequestInfo;
import com.king.game.http.handler.*;
import com.king.game.http.parser.Parser;
import com.king.game.service.GameContext;
import com.sun.net.httpserver.HttpExchange;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.net.URI;

import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;

/**
 * Created by freyliis
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(  URI.class )
public class HandlerFactoryImplTest {

    HandlerFactoryImpl objectUnderTest = new HandlerFactoryImpl();
    @Mock
    Parser parser;
    @Mock
    GameContext gameContext;
    @Mock
    HttpExchange httpExchange;

    @Before
    public void setUp(){
        URI uri = PowerMockito.mock(URI.class);
        MockitoAnnotations.initMocks(this);
        when(httpExchange.getRequestURI()).thenReturn(uri);
        when(uri.getPath()).thenReturn("");
    }

    @Test
    public void shouldReturnLoginRequesthHandler(){
        when(parser.parseRequest(anyString())).thenReturn(RequestInfo.LOGIN);
        final RequestHandler requestHandler = objectUnderTest.getRequestHandler(httpExchange, parser, gameContext);
        Assert.assertThat(requestHandler, CoreMatchers.instanceOf(GetLoginRequestHandler.class));
    }

    @Test
    public void shouldReturnHighscoreListRequestHandler(){
        when(parser.parseRequest(anyString())).thenReturn(RequestInfo.HIGHSCORE);
        final RequestHandler requestHandler = objectUnderTest.getRequestHandler(httpExchange, parser, gameContext);
        Assert.assertThat(requestHandler, CoreMatchers.instanceOf(GetHighscoreListRequestHandler.class));
    }

    @Test
    public void shouldReturnPostScoreRequestHandler(){
        when(parser.parseRequest(anyString())).thenReturn(RequestInfo.POST_SCORE);
        final RequestHandler requestHandler = objectUnderTest.getRequestHandler(httpExchange, parser, gameContext);
        Assert.assertThat(requestHandler, CoreMatchers.instanceOf(PostScoreRequestHandler.class));
    }

    @Test
    public void shouldReturnNotValidRequestHandler(){
        when(parser.parseRequest(anyString())).thenReturn(RequestInfo.EMPTY);
        final RequestHandler requestHandler = objectUnderTest.getRequestHandler(httpExchange, parser, gameContext);
        Assert.assertThat(requestHandler, CoreMatchers.instanceOf(NotValidRequestHandler.class));
    }

}