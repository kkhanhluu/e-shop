package eshop.orderservice.core.exception;

public class InvalidEventException extends RuntimeException {
    public InvalidEventException(String message) {
        super("Invalid event: %s".formatted(message));
    }

    public InvalidEventException() {
    }
}