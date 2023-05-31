package com.udacity.boogle.maps.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Address not found")
public class AddressNotFound extends RuntimeException {
    public AddressNotFound() {
        super();
    }

    public AddressNotFound(String message) {
        super(message);
    }
}
