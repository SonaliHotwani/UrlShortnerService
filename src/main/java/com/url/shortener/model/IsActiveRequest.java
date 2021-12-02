package com.url.shortener.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class IsActiveRequest {
    String url;
    Date requestDate;
}
