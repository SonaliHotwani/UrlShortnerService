package com.url.shortener.exception;

public class UrlNotFoundException extends Throwable {
    public UrlNotFoundException() {
        super("Url Not Found");
    }
}
