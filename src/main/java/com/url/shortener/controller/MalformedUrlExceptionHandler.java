package com.url.shortener.controller;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.server.exceptions.ExceptionHandler;

import javax.inject.Singleton;
import java.net.MalformedURLException;

@Singleton
public class MalformedUrlExceptionHandler implements ExceptionHandler<MalformedURLException, HttpResponse> {
    @Override
    public HttpResponse handle(HttpRequest request, MalformedURLException exception) {
        String errorMessageBody = "{\" message \": \" " + exception.getMessage() + "\"}";
        return HttpResponse.badRequest(errorMessageBody);
    }
}
