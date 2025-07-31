package dev.ryone.zenbily.exception;

import lombok.Getter;

@Getter
public class IllegalUserIdException extends RuntimeException {

    private final String causeValue;

    public IllegalUserIdException(RuntimeException cause, String value) {
        super(cause);
        this.causeValue = value;
    }
}
