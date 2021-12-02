package com.url.shortener.controller;

import com.url.shortener.model.ShortenedUrlResponse;
import com.url.shortener.model.UrlActive;
import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest
class UrlShortnerControllerIntegrationTest {

    @Inject
    @Client("/")
    HttpClient client;

    @Test
    void ShouldReturnShortenedUrl() {
        final String longUrl = "http://localhost/asdfghwertyuzxcvbn/newlyCreatedResource";
        final HttpResponse<ShortenedUrlResponse> response = client.toBlocking().exchange(
                HttpRequest.POST("/shorten/url", "{\"url\": \"" + longUrl + "\"}"),
                Argument.of(ShortenedUrlResponse.class)
        );

        assertEquals(HttpStatus.OK, response.status());
        assertEquals(new ShortenedUrlResponse("http://urlshortner.com/b", longUrl), response.body());
    }

    @Test
    void ShouldReturnBadRequestForInvalidUrl() {
        final String longUrl = "InvalidUrl";
        try {
            client.toBlocking().exchange(
                    HttpRequest.POST("/shorten/url", "{\"url\": \"" + longUrl + "\"}"),
                    Argument.of(String.class)
            );
        } catch (HttpClientResponseException ex) {
            assertEquals(HttpStatus.BAD_REQUEST, ex.getStatus());
        }
    }

    @Test
    void shouldReturnExpiredForUrl() {
        final String longUrl = "http://localhost/asdfghwertyuzxcvbn/newlyCreatedResource";
        final HttpResponse<UrlActive> response = client.toBlocking().exchange(
                HttpRequest.POST("/isActive", "{\"url\": \"" + longUrl + "\"}"),
                Argument.of(UrlActive.class)
        );

        assertEquals(HttpStatus.OK, response.status());
        assertFalse(response.body().isActive);
    }

    @Test
    void shouldReturnActiveForUrl() {
        final String longUrl = "http://localhost/asdfghwertyuzxcvbn/newlyCreatedResource";
        final HttpResponse<UrlActive> response = client.toBlocking().exchange(
                HttpRequest.POST("/isActive", "{\"url\": \"" + longUrl + "\"}"),
                Argument.of(UrlActive.class)
        );

        assertEquals(HttpStatus.OK, response.status());
        assertTrue(response.body().isActive);
    }

    @Test
    void shouldReturnNotFoundIfUrlWasNotCaptured() {
        final String longUrl = "http://localhost/asdfghwertyuzxcvbn/newlyCreatedResource";
        final HttpResponse<UrlActive> response = client.toBlocking().exchange(
                HttpRequest.POST("/isActive", "{\"url\": \"" + longUrl + "\"}"),
                Argument.of(UrlActive.class)
        );

        assertEquals(HttpStatus.NOT_FOUND, response.status());
    }
}
