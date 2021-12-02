package com.url.shortener.service;

import com.url.shortener.exception.UrlNotFoundException;
import com.url.shortener.model.IsActiveRequest;
import com.url.shortener.model.UrlWithTimestamp;
import io.micronaut.context.annotation.Value;

import javax.inject.Singleton;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@Singleton
public class ShortenUrlService {

    private String domain;
    private HashMap<Integer, UrlWithTimestamp> urlsWithIds = new HashMap<>();
    private int id = 1;

    public ShortenUrlService(@Value("${url.shortner.domain}") String domain) {
        this.domain = domain;
    }

    public String shorten(String longUrl) {
        Integer key;
        if (urlsWithIds.containsValue(longUrl)) {
            key = urlsWithIds.entrySet().stream().filter(entry -> entry.getValue().equals(longUrl))
                    .findFirst().get().getKey();
        } else
            key = id;
        String shortUrl = domain + "/" + getShortPath(key);
        urlsWithIds.put(id++, new UrlWithTimestamp(longUrl, new Date()));
        return shortUrl;
    }

    private String getShortPath(Integer key) {
        char[] map = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
        StringBuilder shortPath = new StringBuilder();
        while (key > 0) {
            shortPath.append(map[key % 62]);
            key = key / 62;
        }
        return shortPath.reverse().toString();
    }

    public boolean isActive(IsActiveRequest request) throws UrlNotFoundException {
        final String longUrl = request.getUrl();

        final Optional<Map.Entry<Integer, UrlWithTimestamp>> urlWithTimestampEntry = urlsWithIds.entrySet().stream().filter(entry -> entry.getValue().getUrl().equals(longUrl))
                .findFirst();
        if (!urlWithTimestampEntry.isPresent()) {
            throw new UrlNotFoundException();
        }
        final Date capturedTimestamp = urlWithTimestampEntry.get().getValue().getCapturedTimestamp();

        final Instant after5secs = capturedTimestamp.toInstant().plusSeconds(5);

        return request.getRequestDate().toInstant().isBefore(after5secs);
    }
}
