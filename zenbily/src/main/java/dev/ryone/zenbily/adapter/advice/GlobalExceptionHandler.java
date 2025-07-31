package dev.ryone.zenbily.adapter.advice;

import dev.ryone.zenbily.exception.IllegalUserIdException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalUserIdException.class)
    public ResponseEntity<ErrorResponse> handleIllegalUUIDValue(IllegalUserIdException e) {
        return ResponseEntity.badRequest()
                .body(new ErrorResponse("E-01",
                        String.format("Cause by %s: %s", e.getCauseValue(), e.getCause())));
    }

    public record ErrorResponse(String code, String message) {

    }
}
