package com.king.game.http.handler;

import com.king.game.http.RequestMethod;
import com.sun.net.httpserver.HttpExchange;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.mockito.Mockito.*;

/**
 * Created by freyliis
 */
@RunWith(MockitoJUnitRunner.class)
public class AbstractRequestHandlerTest {

    @Mock
    HttpExchange httpExchange;

    AbstractRequestHandler abstractRequestHandler = new AbstractRequestHandler() {
        @Override
        public RequestMethod getValidRequestMethod() {
            return null;
        }

        @Override
        public Response handleSingleRequest(HttpExchange httpExchange) {
            return null;
        }
    };

    @Test
    public void shouldReturnTrueIfArgumentAndRequestMethodAreTheSame() {
        when(httpExchange.getRequestMethod()).thenReturn(RequestMethod.GET.toString());
        final boolean validateRequestMethod = abstractRequestHandler.validateRequestMethod(httpExchange, RequestMethod.GET);
        Assert.assertThat(validateRequestMethod, CoreMatchers.is(Boolean.TRUE));
    }

    @Test
    public void shouldReturnFalseIfArgumentAndRequestMethodAreNotTheSame() {
        when(httpExchange.getRequestMethod()).thenReturn(RequestMethod.GET.toString());
        final boolean validateRequestMethod = abstractRequestHandler.validateRequestMethod(httpExchange, RequestMethod.POST);
        Assert.assertThat(validateRequestMethod, CoreMatchers.is(Boolean.FALSE));
    }

}