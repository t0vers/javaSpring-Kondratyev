package org.example.skillbox_mod4.adapter.web.error;

import org.springframework.http.HttpStatus;

public class DontHavePermissionException extends CommonException {

    public DontHavePermissionException(String message) {
        super(ErrorCode.DONT_HAVE_PERMISSION, HttpStatus.FORBIDDEN, message);
    }
}
