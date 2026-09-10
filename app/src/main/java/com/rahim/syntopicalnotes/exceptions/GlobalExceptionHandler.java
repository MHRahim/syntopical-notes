package com.rahim.syntopicalnotes.exceptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.rahim.syntopicalnotes.domains.dto.core.ValidationExceptionDto;
import com.rahim.syntopicalnotes.utils.ResponseFormatter;

import lombok.AllArgsConstructor;

@RestControllerAdvice
@AllArgsConstructor
public class GlobalExceptionHandler {

    private final ResponseFormatter responseFormatter;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidation(MethodArgumentNotValidException ex) {
        Map<String, List<String>> errorMap = new HashMap<>();
        for (var fe : ex.getBindingResult().getFieldErrors()) {
            errorMap.computeIfAbsent(fe.getField(), k -> new ArrayList<>()).add(fe.getDefaultMessage());
        }
        String message = errorMap.isEmpty() ? "" : errorMap.values().iterator().next().get(0);
        List<Map<String, String>> globalErrors = ex.getBindingResult().getGlobalErrors().stream()
                .map(ge -> Map.of(
                        "object", ge.getObjectName(),
                        "message", ge.getDefaultMessage()))
                .toList();
        ValidationExceptionDto validationExceptionDto = new ValidationExceptionDto(errorMap);
        return this.responseFormatter.send(422, message, Map.of(
                "errors", validationExceptionDto.errors(),
                "globalErrors", globalErrors));
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundExceptions() {
        return this.responseFormatter.send(404, "Content not found.", null);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        String message = ex.getMostSpecificCause() != null
                ? ex.getMostSpecificCause().getMessage()
                : ex.getMessage();

        System.err.println(message);

        return this.responseFormatter.send(400, "Bad request", null);
    }
}
