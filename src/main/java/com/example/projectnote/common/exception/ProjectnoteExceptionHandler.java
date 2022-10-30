package com.example.projectnote.common.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ProjectnoteExceptionHandler {

    private final Logger LOGGER = LoggerFactory.getLogger(ProjectnoteExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Map<String, String>> ExceptionHandler(Exception e){
        HttpHeaders responseHeaders = new HttpHeaders();
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        LOGGER.info("Call ExceptionHandler in the Advice");

        Map<String, String> map = new HashMap<>();
        map.put("Error type", httpStatus.getReasonPhrase());
        map.put("code", "400");
        map.put("message", "Error");

        return new ResponseEntity<>(map, responseHeaders, httpStatus);
    }

    @ExceptionHandler(value = ProjectnoteException.class)
    public ResponseEntity<Map<String, String>> ExceptionHandler(ProjectnoteException e){
        HttpHeaders responseHeader = new HttpHeaders();

        Map<String, String> map = new HashMap<>();
        map.put("Error Type", e.getHttpStatusType());
        map.put("Error code", Integer.toString(e.getHttpStatusCode())); //type of getHttpStatusCode is Int
        map.put("message", e.getMessage());

        return new ResponseEntity<>(map, responseHeader, e.getHttpStatus());
    }
}
