package com.url.shortener.controller;

import com.url.shortener.exception.UrlNotFoundException;
import com.url.shortener.model.IsActiveRequest;
import com.url.shortener.model.Request;
import com.url.shortener.model.ShortenedUrlResponse;
import com.url.shortener.model.UrlActive;
import com.url.shortener.service.ShortenUrlService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

@Controller
public class UrlShortnerController {

    private ShortenUrlService service;

    public UrlShortnerController(ShortenUrlService service) {
        this.service = service;
    }

    @Post(value = "/shorten/url")
    public HttpResponse<ShortenedUrlResponse> shorten(@Body Request request) throws MalformedURLException {
        final URL url = new URL(request.getUrl());
        final String longUrl = url.toString();
        String shortUrl = service.shorten(longUrl);
        return HttpResponse.ok(new ShortenedUrlResponse(shortUrl, longUrl));
    }

    @Post(value = "/isActive")
    public HttpResponse<UrlActive> isUrlActive(@Body Request request) throws UrlNotFoundException {
        boolean isActive = service.isActive(new IsActiveRequest(request.getUrl(), new Date()));
        return HttpResponse.ok(new UrlActive(isActive));
    }
}


