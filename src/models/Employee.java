package models;

import Exceptions.*;

public class Employee<T> implements Comparable<Employee<T>> {
    private T employeeId;
    private String name;
    private String department;
    private double salary;
    private double performanceRating;
    private int yearsOfExperience;
    private boolean isActive;

    public Employee(T employeeId, String name, String department, double salary,
                    double performanceRating, int yearsOfExperience, boolean isActive) throws InvalidEmployeeNameException, InvalidDepartmentException, InvalidSalaryException, InvalidRatingException, InvalidYearsofExperienceException {
        this.employeeId = employeeId;
        this.name = (name==null?"NaN":name);
        this.department = (department==null?"NaN":department);
        this.salary = (salary>=0)?salary:0;
        this.performanceRating = Math.max(0,Math.min(performanceRating,5));
        this.yearsOfExperience = Math.max(yearsOfExperience, 0);
        this.isActive = isActive;

        if (name==null||name.isEmpty()) {
            throw new InvalidEmployeeNameException();
        }
        if (department==null||department.isEmpty()){
            throw new InvalidDepartmentException();
        }
        if (salary<0){
            throw new InvalidSalaryException();
        }
        if (performanceRating<0||performanceRating>5){
            throw new InvalidRatingException();
        }
        if (yearsOfExperience<0){
            throw new InvalidYearsofExperienceException();
        }
    }

    public T getEmployeeId() { return employeeId; }
    public String getName() { return name; }
    public String getDepartment() { return department; }
    public double getSalary() { return salary; }
    public double getPerformanceRating() { return performanceRating; }
    public int getYearsOfExperience() { return yearsOfExperience; }
    public boolean isActive() { return isActive;}



    //Data validation
    public void setName(String name) throws InvalidEmployeeNameException {
        this.name = (name==null?"NaN":name);
        if (name==null||name.isEmpty()) {
            throw new InvalidEmployeeNameException();
        }

    }
    public void setDepartment(String department) throws InvalidDepartmentException {
        this.department = (department==null?"NaN":department);
        if (department==null||department.isEmpty()){
            throw new InvalidDepartmentException();
        }

    }
    public void setSalary(double salary) throws InvalidSalaryException {
        this.salary = (salary>=0)?salary:0;
        if (salary<0){
            throw new InvalidSalaryException();
        }
    }
    public void setPerformanceRating(double rating) throws InvalidRatingException {
        this.performanceRating = Math.max(0,Math.min(rating,5));
        if (rating<0||rating>5){
            throw new InvalidRatingException();
        }

    }
    public void setYearsOfExperience(int years) throws InvalidYearsofExperienceException {
        this.yearsOfExperience = Math.max(years, 0);
        if (years<0){
            throw new InvalidYearsofExperienceException();
        }

    }
    public void setActive(boolean active) { this.isActive = active; }

    @Override
    public int compareTo(Employee<T> other) {
        return Integer.compare(other.yearsOfExperience, this.yearsOfExperience);
    }

    @Override
    public String toString() {
        return String.format("%-10s %-20s %-15s %-10.2f %-10.1f %-10d %-10s",
                employeeId, name, department, salary, performanceRating, yearsOfExperience, isActive);
    }
}