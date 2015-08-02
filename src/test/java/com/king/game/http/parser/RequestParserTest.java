package com.king.game.http.parser;

import com.king.game.http.RequestInfo;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

public class RequestParserTest {

    RequestParser objectUnderTest = new RequestParser();

   ///<userid>/login
    @Test
    public void shouldReturnProperUserIdForLoginRequest() {
        final String value = "<userId>";
        String path = value+"/login";
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
        RequestInfo kind = objectUnderTest.parseRequest(path);
        Assert.assertThat(kind, CoreMatchers.is(RequestInfo.LOGIN));
    }

    @Test
    public void shouldReturnHighScoreMessageInfo() {
        String path =  "<levelid>/highscorelist";
        RequestInfo kind = objectUnderTest.parseRequest(path);
        Assert.assertThat(kind, CoreMatchers.is(RequestInfo.HIGHSCORE));
    }

    public void shouldReturnProperLevelIdForPostScoreRequest() {
        final String value = "<levelid>";
        String path = value+"/score?sessionkey=<sessionkey>";
        String levelId = objectUnderTest.parsePostScoreRequestForLevelId(path);
        Assert.assertThat(levelId, CoreMatchers.is(value));
    }

    @Test
    public void shouldReturnProperSessionIdForPostScoreRequest() {
        final String value = "<sessionkey>";
        String path = "<levelid>/score?sessionkey="+value;
        String sessionId = objectUnderTest.parsePostScoreRequestForSessionKey(path);
        Assert.assertThat(sessionId, CoreMatchers.is(value));
    }



}