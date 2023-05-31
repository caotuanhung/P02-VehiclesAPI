package com.udacity.boogle.maps.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Unexpected exception when saving address")
public class SavingAddressException extends RuntimeException {
    public SavingAddressException() {
        super();
    }

    public SavingAddressException(String message) {
        super(message);
    }
}
