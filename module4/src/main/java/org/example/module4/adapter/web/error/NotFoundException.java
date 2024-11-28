package org.example.skillbox_mod4.adapter.web.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class NotFoundException extends CommonException {
    public NotFoundException(String message) {
        super(ErrorCode.NOT_FOUND_EXCEPTION, HttpStatus.NOT_FOUND, message);
    }
}
