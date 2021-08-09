package com.url.shortener.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;


@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ShortenedUrlResponse {
    private String shortUrl;
    private String longUrl;
}