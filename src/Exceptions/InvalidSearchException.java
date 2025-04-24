package Exceptions;

public class InvalidSearchException extends RuntimeException {
    public String message;

    public InvalidSearchException() {
        super("System Exception: The search parameter is invalid");
        this.message = "System Exception: The search parameter is invalid\nHint: Make sure that the value is not empty";
    }
}


