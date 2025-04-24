package Exceptions;

public class InvalidEmployeeNameException extends Exception {
    public String message;

    public InvalidEmployeeNameException() {
        super("System Exception: The employee name data about to be inserted is invalid");
        this.message = "System Exception: The employee name data about to be inserted is invalid\nHint: Make sure that the value is not empty";
    }
}
