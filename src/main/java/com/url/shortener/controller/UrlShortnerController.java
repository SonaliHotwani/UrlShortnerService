package com.url.shortener.controller;

import com.url.shortener.model.Request;
import com.url.shortener.model.ShortenedUrlResponse;
import com.url.shortener.service.ShortenUrlService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;

@Controller
public class UrlShortnerController {

    private ShortenUrlService service;

    public UrlShortnerController(ShortenUrlService service) {
        this.service = service;
    }

    @Post(value = "/shorten/url")
    public HttpResponse<ShortenedUrlResponse> shorten(@Body Request request) {
        final String longUrl = request.getUrl();
        String shortUrl = service.shorten(longUrl);
        return HttpResponse.ok(new ShortenedUrlResponse(shortUrl, longUrl));
    }
}


