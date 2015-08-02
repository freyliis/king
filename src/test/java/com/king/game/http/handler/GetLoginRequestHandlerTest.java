package com.king.game.http.handler;

import com.king.game.http.parser.Parser;
import com.king.game.service.GameContext;
import com.king.game.service.LoginService;
import com.sun.net.httpserver.HttpExchange;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.net.URI;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

/**
 * Created by freyliis
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(  URI.class )
public class GetLoginRequestHandlerTest {

    @Mock
    HttpExchange httpExchange;
    @Mock
    Parser parser;
    @Mock
    GameContext gameContext;
    @Mock
    LoginService loginService;

    GetLoginRequestHandler objectUnderTest;

    ;

    @Before
    public void setUp(){
        URI uri = PowerMockito.mock(URI.class);
        MockitoAnnotations.initMocks(this);
        when(httpExchange.getRequestURI()).thenReturn(uri);
        when(uri.getPath()).thenReturn("");

    }


    @Test
    public void shouldReturnStatusFailedResponseWhenParseException() {
        objectUnderTest = new GetLoginRequestHandler(gameContext, parser);
        when(parser.parseLoginUserRequest(anyString())).thenReturn("wrongUserId");
        final Response response = objectUnderTest.handleSingleRequest(httpExchange);
        Assert.assertThat(response.getStatus(), CoreMatchers.is(RequestHandler.HTTP_FAILED_STATUS));
    }

    @Test
    public void shouldReturnStatusOkResponseWhenNotParseException() {
        objectUnderTest = new GetLoginRequestHandler(gameContext, parser);
        when(parser.parseLoginUserRequest(anyString())).thenReturn("1");
        when(gameContext.getLoginService()).thenReturn(loginService);
        when(loginService.login(anyInt())).thenReturn("");
        final Response response = objectUnderTest.handleSingleRequest(httpExchange);
        Assert.assertThat(response.getStatus(), CoreMatchers.is(RequestHandler.HTTP_OK_STATUS));
    }

}