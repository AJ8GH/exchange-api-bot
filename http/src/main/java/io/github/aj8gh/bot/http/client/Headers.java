package io.github.aj8gh.bot.http.client;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public enum Headers {
    CONTENT_TYPE(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE),
    ACCEPT(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE),
    X_AUTHENTICATION("X-Authentication", ""),
    X_APPLICATION("X-Application", ""),
    X_IP("X-IP", "127.0.0.1");

    private final String header;
    private final String value;

    Headers(String header, String value) {
        this.header = header;
        this.value = value;
    }

    public String header() {
        return header;
    }

    public String value() {
        return value;
    }
}
