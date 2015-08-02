package com.king.game.http.handler;

import com.king.game.http.parser.Parser;
import com.king.game.service.GameContext;
import com.king.game.service.ScoreService;
import com.king.model.Score;
import com.sun.net.httpserver.HttpExchange;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.net.URI;
import java.util.List;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

/**
 * Created by freyliis
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(  URI.class )
public class PostScoreRequestHandlerTest {

    @Mock
    HttpExchange httpExchange;
    @Mock
    Parser parser;
    @Mock
    GameContext gameContext;
    @Mock
    ScoreService scoreService;
    @Mock
    List<Score> mockedList;

    PostScoreRequestHandler objectUnderTest;

    @Before
    public void setUp(){
        URI uri = PowerMockito.mock(URI.class);
        MockitoAnnotations.initMocks(this);
        when(httpExchange.getRequestURI()).thenReturn(uri);
        when(uri.getPath()).thenReturn("");

    }

    @Test
    public void shouldReturnStatusFailedResponseWhenParseExceptionForLevelId() {
        objectUnderTest = new PostScoreRequestHandler(gameContext, parser);
        when(parser.parsePostScoreRequestForLevelId(anyString())).thenReturn("wrongLevelId");
        final Response response = objectUnderTest.handleSingleRequest(httpExchange);
        Assert.assertThat(response.getStatus(), CoreMatchers.is(RequestHandler.HTTP_FAILED_STATUS));
    }

    @Test
    public void shouldReturnStatusFailedResponseWhenParseExceptionForUserId() {
        objectUnderTest = new PostScoreRequestHandler(gameContext, parser);
        when(parser.parsePostScoreRequestBody(httpExchange)).thenReturn("wrongLevelId");
        final Response response = objectUnderTest.handleSingleRequest(httpExchange);
        Assert.assertThat(response.getStatus(), CoreMatchers.is(RequestHandler.HTTP_FAILED_STATUS));
    }

    @Test
    public void shouldReturnStatusOkResponseWhenNotParseException() {
        objectUnderTest =  Mockito.spy(new PostScoreRequestHandler(gameContext, parser));
        when(parser.parsePostScoreRequestBody(httpExchange)).thenReturn("1");
        when(parser.parsePostScoreRequestForLevelId(anyString())).thenReturn("1");
        when(gameContext.getScoreService()).thenReturn(scoreService);
        final Response response = objectUnderTest.handleSingleRequest(httpExchange);
        Assert.assertThat(response.getStatus(), CoreMatchers.is(RequestHandler.HTTP_OK_STATUS));
    }

}