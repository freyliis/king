package com.king.game.http.parser.impl;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by freyliis
 */
public class LoginRequestParserImplTest {

    LoginRequestParserImpl objectUnderTest = new LoginRequestParserImpl(new ParserHandler("/"));

    ///<userid>/login
    @Test
    public void shouldReturnProperUserIdForLoginRequest() {
        final String value = "<userId>";
        String path = value+"/login";
        String userId = objectUnderTest.parseLoginUserRequest(path);
        Assert.assertThat(userId, CoreMatchers.is(value));
    }

}