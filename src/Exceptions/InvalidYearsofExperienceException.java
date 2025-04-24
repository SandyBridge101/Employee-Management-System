package Exceptions;

public class InvalidYearsofExperienceException extends Exception {
    public String message;

    public InvalidYearsofExperienceException() {
        super("System Exception: The employee experience data about to be inserted is invalid");
        this.message = "System Exception: The employee experience data about to be inserted is invalid\nHint: Make sure that the value is greater than 0";
    }
}
