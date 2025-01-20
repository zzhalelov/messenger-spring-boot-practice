package kz.zzhalelov.messengerspringbootpractice.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}