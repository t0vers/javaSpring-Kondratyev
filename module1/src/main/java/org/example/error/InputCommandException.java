package org.example.error;

public class InputCommandException extends CommonException{
    public InputCommandException(String message) {
        super(ErrorCode.INPUT_COMMAND_ERROR, message);
    }
}
