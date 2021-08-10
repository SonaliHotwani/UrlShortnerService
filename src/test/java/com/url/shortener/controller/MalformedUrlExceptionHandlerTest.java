package com.url.shortener.controller;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class MalformedUrlExceptionHandlerTest {

    @Test
    void shouldReturnBadRequestForMalformedUrlException() {
        final MalformedUrlExceptionHandler handler = new MalformedUrlExceptionHandler();
        final String message = "Malformed Url";
        final HttpResponse httpResponse = handler.handle(mock(HttpRequest.class), new MalformedURLException(message));
        String errorMessageBody = "{\" message \": \" " + message + "\"}";
        assertEquals(HttpStatus.BAD_REQUEST, httpResponse.status());
        assertEquals(errorMessageBody, httpResponse.body());
    }

}
