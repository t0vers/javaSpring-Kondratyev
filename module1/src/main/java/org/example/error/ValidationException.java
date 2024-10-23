package org.example.error;

public class ValidationException extends CommonException {
    public ValidationException(String message) {
        super(ErrorCode.VALIDATION_ERROR, message);
    }
}
