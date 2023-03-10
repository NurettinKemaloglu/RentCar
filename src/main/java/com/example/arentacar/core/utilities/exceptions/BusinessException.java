package com.example.arentacar.core.utilities.exceptions;

public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message); // super base sınıf demektir.
    }
}
