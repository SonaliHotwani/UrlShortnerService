package com.url.shortener.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@AllArgsConstructor
@Getter
public class UrlWithTimestamp {
    String url;
    Date capturedTimestamp;
}
