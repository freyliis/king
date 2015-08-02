package com.king.game.http.parser.impl;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by freyliis
 */
public class PostScoreRequestParserImplTest {

    PostScoreRequestParserImpl objectUnderTest = new PostScoreRequestParserImpl(new ParserHandler("/"));

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