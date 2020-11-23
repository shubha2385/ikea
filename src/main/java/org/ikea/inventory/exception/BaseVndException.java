package org.ikea.inventory.exception;

import lombok.Getter;

import java.util.Objects;

@Getter
public abstract class BaseVndException extends RuntimeException {

    private final String field;
    private final String code;
    private final String message;

    public BaseVndException(String errorCode, String message) {
        this("", errorCode, message);
    }

    public BaseVndException(String field, String errorCode, String message) {
        super(message);
        Objects.requireNonNull(errorCode);
        this.field = field;
        this.message = message;
        this.code = errorCode;
    }
}
