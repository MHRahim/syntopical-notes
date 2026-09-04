package com.rahim.syntopicalnotes.utils;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ResponseFormatter {

    public <T> ResponseEntity<Response<T>> send(int statusCode, String message, T data) {
        String statusString = statusCode < 400 ? "Success!" : "Error";

        ResponseMetadata respMetadata = new ResponseMetadata(statusCode, message, statusString);
        Response<T> resp = new  Response<>(respMetadata, data);

        return ResponseEntity
                .status(statusCode)
                .body(resp);
    }

    public record ResponseMetadata(int code, String message, String statusString){};

    public record Response<T>(ResponseMetadata meta, T data){}

}
