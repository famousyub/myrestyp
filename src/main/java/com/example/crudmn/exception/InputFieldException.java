package com.example.crudmn.exception;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.Map;
import java.util.stream.Collectors;


public class InputFieldException extends RuntimeException {
    public BindingResult getBindingResult() {
        return bindingResult;
    }

    public Map<String, String> getErrorsMap() {
        return errorsMap;
    }

    private final BindingResult bindingResult;
    private final Map<String, String> errorsMap;

    public InputFieldException(BindingResult bindingResult) {
        this.bindingResult = bindingResult;
        this.errorsMap = bindingResult.getFieldErrors().stream()
                .collect(Collectors.toMap(
                        fieldError -> fieldError.getField() + "Error",
                        FieldError::getDefaultMessage
                ));
    }
}