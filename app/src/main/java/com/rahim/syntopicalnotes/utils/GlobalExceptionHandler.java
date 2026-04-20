package com.rahim.syntopicalnotes.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.rahim.syntopicalnotes.domains.dto.core.ValidationExceptionDto;

import org.springframework.web.bind.MethodArgumentNotValidException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    private final ResponseFormatter responseFormatter;

	public GlobalExceptionHandler(ResponseFormatter responseFormatter) {
		this.responseFormatter = responseFormatter;
	}


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex) {
        // Collect all the "message" strings from your DTO annotations

        List<FieldError> errors = ex.getBindingResult().getFieldErrors();
        Map<String, List<String>> errorMap = new HashMap<String, List<String>>();
        String message = "";

        for (FieldError e : errors) {
            String field = e.getField();
            if(errorMap.containsKey(field))  {
                message = message.equals("") ? e.getDefaultMessage() : message;
                errorMap.get(field).add(e.getDefaultMessage());
                continue;
            }

            errorMap.put(field, List.of(e.getDefaultMessage()));
        }
        
        ValidationExceptionDto validationExceptionDto = new ValidationExceptionDto(errorMap);
        return responseFormatter.send(422, message, validationExceptionDto);
    }
}
