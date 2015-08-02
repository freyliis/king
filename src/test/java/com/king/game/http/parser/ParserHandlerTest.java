package com.king.game.http.parser;

import com.king.game.http.parser.impl.ParserHandler;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by freyliis
 */
public class ParserHandlerTest {

    ParserHandler objectUnderTest;

    @Before
    public void setUp(){
        final String context = "/";
        objectUnderTest = new ParserHandler(context);
    }

    @Test
    public void shouldRemoveContext() {
        final String context = "/";
        objectUnderTest = new ParserHandler(context);
        final String url = "test";
        final String result = objectUnderTest.removeContextFromPath(context + url);
        Assert.assertThat(result, CoreMatchers.is(url));
    }

    @Test
    public void shouldNotRemoveContext() {
        final String context = "/";
        objectUnderTest = new ParserHandler(context);
        final String url = "test"+context;
        final String result = objectUnderTest.removeContextFromPath(url);
        Assert.assertThat(result, CoreMatchers.is(url));
    }





}