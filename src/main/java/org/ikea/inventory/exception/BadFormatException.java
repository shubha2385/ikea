package org.ikea.inventory.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class BadFormatException extends BaseVndException {

    private static final long serialVersionUID = 1L;

    public BadFormatException(String code, String message) {
        super(code, message);
    }
}

