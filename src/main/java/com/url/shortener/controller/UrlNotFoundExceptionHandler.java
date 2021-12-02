package com.url.shortener.controller;

import com.url.shortener.exception.UrlNotFoundException;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.server.exceptions.ExceptionHandler;

import javax.inject.Singleton;

@Singleton
public class UrlNotFoundExceptionHandler implements ExceptionHandler<UrlNotFoundException, HttpResponse> {

    @Override
    public HttpResponse handle(HttpRequest request, UrlNotFoundException exception) {
        String body = "{\" message \": \" " + exception.getMessage() + "\"}";
        return HttpResponse.notFound(body);
    }
}
