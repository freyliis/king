package com.king.game.http.parser;

import com.king.game.http.RequestInfo;
import com.king.game.http.parser.impl.ParserHandler;
import com.king.game.http.parser.impl.RequestParserImpl;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

public class RequestParserTest {

    RequestParserImpl objectUnderTest = new RequestParserImpl(new ParserHandler("/"));

    @Test
    public void shouldReturnLoginMessageInfo() {
        String path =  "<userid>/login";
        RequestInfo kind = objectUnderTest.parseRequest(path);
        Assert.assertThat(kind, CoreMatchers.is(RequestInfo.LOGIN));
    }

    @Test
    public void shouldReturnHighScoreMessageInfo() {
        String path =  "<levelid>/highscorelist";
        RequestInfo kind = objectUnderTest.parseRequest(path);
        Assert.assertThat(kind, CoreMatchers.is(RequestInfo.HIGHSCORE));
    }




}