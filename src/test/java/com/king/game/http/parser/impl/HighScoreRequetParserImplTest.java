package com.king.game.http.parser.impl;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by freyliis
 */
public class HighScoreRequetParserImplTest {

    HighScoreRequestParserImpl objectUnderTest = new HighScoreRequestParserImpl(new ParserHandler("/"));

    ///<levelid>/highscorelist
    @Test
    public void shouldReturnProperLevelIdForHighScoreListRequest() {
        final String value = "<levelid>";
        String path =  value+"/highscorelist";
        String userId = objectUnderTest.parseHighScoreListRequest(path);
        Assert.assertThat(userId, CoreMatchers.is(value));
    }
}
