package dev.ryone.zenbily.exception;

import lombok.Getter;

@Getter
public class IllegalUUIDException extends RuntimeException {

    private final String causeValue;

    public IllegalUUIDException(RuntimeException cause, String value) {
        super(cause);
        this.causeValue = value;
    }
}
