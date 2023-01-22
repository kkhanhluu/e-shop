package eshop.api.http;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class HttpErrorInfo {
    private final LocalDateTime timestamp;
    private final String path;
    private final HttpStatus httpStatus;
    private final String message;

    public HttpErrorInfo() {
        this.timestamp = null;
        this.path = null;
        this.httpStatus = null;
        this.message = null;
    }

    public HttpErrorInfo(String path, HttpStatus httpStatus, String message) {
        this.timestamp = LocalDateTime.now();
        this.path = path;
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
