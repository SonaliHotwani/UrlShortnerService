package com.url.shortener.service;

import io.micronaut.context.annotation.Value;

import javax.inject.Singleton;
import java.util.HashMap;


@Singleton
public class ShortenUrlService {

    private String domain;
    private HashMap<Integer, String> urlsWithIds = new HashMap<>();
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
        String shortUrl = domain + "/" +getShortUrl(key);
        urlsWithIds.put(id++, longUrl);
        return shortUrl;
    }

    private String getShortUrl(Integer key) {
        char[] map = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
        StringBuilder shortUrl = new StringBuilder();
        while (key > 0) {
            shortUrl.append(map[key % 62]);
            key = key / 62;
        }
        return shortUrl.reverse().toString();
    }
}
