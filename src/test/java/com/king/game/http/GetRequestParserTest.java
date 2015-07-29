package com.king.game.http;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

public class GetRequestParserTest {

    GetRequestParser objectUnderTest = new GetRequestParser();

   ///<userid>/login
    @Test
    public void shouldReturnProperUserIdForLoginRequest() {
        final String value = "<userId>";
        String path =  value+"/login";
        String userId = objectUnderTest.parseLoginUserRequest(path);
        Assert.assertThat(userId, CoreMatchers.is(value));
    }

///<levelid>/highscorelist
    @Test
    public void shouldReturnProperLevelIdForHighScoreListRequest() {
        final String value = "<levelid>";
        String path =  value+"/highscorelist";
        String userId = objectUnderTest.parseHighScoreListRequest(path);
        Assert.assertThat(userId, CoreMatchers.is(value));
    }

    @Test
    public void shouldReturnLoginMessageInfo() {
        String path =  "<userid>/login";
        GetRequestInfo kind = objectUnderTest.checkGetRequest(path);
        Assert.assertThat(kind, CoreMatchers.is(GetRequestInfo.LOGIN));
    }

    @Test
    public void shouldReturnHighScoreMessageInfo() {
        String path =  "<levelid>/highscorelist";
        GetRequestInfo kind = objectUnderTest.checkGetRequest(path);
        Assert.assertThat(kind, CoreMatchers.is(GetRequestInfo.HIGHSCORE));
    }



}