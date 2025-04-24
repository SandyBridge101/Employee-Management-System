package Exceptions;

public class InvalidDepartmentException extends Exception{
    public String message;
    public InvalidDepartmentException(){
        super("System Exception: The employee department data about to be inserted is invalid");
        this.message="System Exception: The employee department data about to be inserted is invalid\nHint: Make sure that the value is not empty";
    }
}