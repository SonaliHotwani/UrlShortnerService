package com.url.shortener.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ShortenUrlServiceTest {

    @Test
    void shouldReturnShortUrl() {
        final ShortenUrlService service = new ShortenUrlService("http://testDomain.com");
        final String shortUrl = service.shorten("http://domain.com/longUrl/resource");
        assertEquals("http://testDomain.com/b", shortUrl);
    }
}
