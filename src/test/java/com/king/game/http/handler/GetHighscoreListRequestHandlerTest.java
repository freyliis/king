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
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.when;

/**
 * Created by freyliis
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(  URI.class )
public class GetHighscoreListRequestHandlerTest {
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

    GetHighscoreListRequestHandler objectUnderTest;

 ;

    @Before
    public void setUp(){
        URI uri = PowerMockito.mock(URI.class);
        MockitoAnnotations.initMocks(this);
        when(httpExchange.getRequestURI()).thenReturn(uri);
        when(uri.getPath()).thenReturn("");

    }

    @Test
    public void shouldProperlyParseScoreList(){
        objectUnderTest = new GetHighscoreListRequestHandler(gameContext, parser);
        final int userId1 = 1;
        final int userId2 = 2;
        Score score1 = new Score(userId1, userId1);
        Score score2 = new Score(userId2, userId2);
        StringBuilder result = new StringBuilder();
        result.append(userId1).append("=").append(userId1).append(",").append(userId2).append("=").append(userId2);
        List<Score> scores = new ArrayList<>();
        scores.add(score1);
        scores.add(score2);
        final String scoresCommaSeparatedString = objectUnderTest.parseToCommaSeparatedString(scores);
        Assert.assertThat(scoresCommaSeparatedString, CoreMatchers.is(result.toString()));
    }

    @Test
    public void shouldReturnStatusFailedResponseWhenParseException() {
        objectUnderTest = new GetHighscoreListRequestHandler(gameContext, parser);
        when(parser.parseHighScoreListRequest(anyString())).thenReturn("wrongLevelId");
        final Response response = objectUnderTest.handleSingleRequest(httpExchange);
        Assert.assertThat(response.getStatus(), CoreMatchers.is(RequestHandler.HTTP_FAILED_STATUS));
    }

    @Test
    public void shouldReturnStatusOkResponseWhenNotParseException() {
        objectUnderTest =  Mockito.spy(new GetHighscoreListRequestHandler(gameContext, parser));
        when(parser.parseHighScoreListRequest(anyString())).thenReturn("1");
        when(gameContext.getScoreService()).thenReturn(scoreService);
        when(scoreService.getHighscoreList(anyInt())).thenReturn(mockedList);
        when(objectUnderTest.parseToCommaSeparatedString(anyList())).thenReturn("");
        final Response response = objectUnderTest.handleSingleRequest(httpExchange);
        Assert.assertThat(response.getStatus(), CoreMatchers.is(RequestHandler.HTTP_OK_STATUS));
    }

}