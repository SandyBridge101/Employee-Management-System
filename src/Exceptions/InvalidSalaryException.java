package Exceptions;

public class InvalidSalaryException extends Exception{
    public String message;
    public InvalidSalaryException(){
        super("System Exception: The employee salary data about to be inserted is invalid");
        this.message="System Exception: The employee salary data about to be inserted is invalid\nHint: Make sure that the value is greater than zero";
    }
}



