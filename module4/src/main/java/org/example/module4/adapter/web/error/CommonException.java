package org.example.skillbox_mod4.adapter.web.error;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@Builder
@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CommonException extends RuntimeException{
    ErrorCode errorCode;
    HttpStatus httpStatus;
    String message;
}
