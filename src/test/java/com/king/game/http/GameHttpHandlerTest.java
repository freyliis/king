package com.king.game.http;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

public class GameHttpHandlerTest {

    GameHttpHandler objectUnderTest = new GameHttpHandler();
    @Test
    public void shouldReturnProperLevelId() {
        final String value = "<levelid>";
        String path = value+"/score?sessionkey=<sessionkey>";
        String levelId = objectUnderTest.parsePostScoreMessageForLevelId(path);
        Assert.assertThat(levelId, CoreMatchers.is(value));
    }

    @Test
    public void shouldReturnProperSessionId() {
        final String value = "<sessionkey>";
        String path = "<levelid>/score?sessionkey="+value;
        String sessionId = objectUnderTest.parsePostScoreMessageForSessionKey(path);
        Assert.assertThat(sessionId, CoreMatchers.is(value));
    }

    ///<userid>/login
    @Test
    public void shouldReturnProperUserId() {
        final String value = "<userId>";
        String path =  value+"/login";
        String userId = objectUnderTest.parseLoginUserMessage(path);
        Assert.assertThat(userId, CoreMatchers.is(value));
    }

    @Test
    public void shouldReturnLoginMessageInfo() {
        String path =  "<userid>/login";
        MessageInfo kind = objectUnderTest.checkGetMessage(path);
        Assert.assertThat(kind, CoreMatchers.is(MessageInfo.LOGIN));
    }

    @Test
    public void shouldReturnHighScoreMessageInfo() {
        String path =  "<levelid>/highscorelist";
        MessageInfo kind = objectUnderTest.checkGetMessage(path);
        Assert.assertThat(kind, CoreMatchers.is(MessageInfo.HIGHSCORE));
    }



}