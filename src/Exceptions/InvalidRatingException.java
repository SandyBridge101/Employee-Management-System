package Exceptions;

public class InvalidRatingException extends Exception{
    public String message;
    public InvalidRatingException(){
        super("System Exception: The employee performance data about to be inserted is invalid");
        this.message="System Exception: The employee performance data about to be inserted is invalid\nHint: Make sure that the value is within the range 0-5";
    }
}
