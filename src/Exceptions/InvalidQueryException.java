package Exceptions;

public class InvalidQueryException extends RuntimeException {
    public String message;

    public InvalidQueryException() {
        super("System Exception: The query parameter is invalid");
        this.message = "System Exception: The query parameter is invalid\nHint: Make sure that the value is not empty";
    }
}