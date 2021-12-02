package com.url.shortener.model;

import lombok.Getter;

import java.util.Objects;

@Getter
public class Request {
    String url;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request request = (Request) o;
        return url.equals(request.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url);
    }
}
