package models;

public class Employee<T> implements Comparable<Employee<T>> {
    private T employeeId;
    private String name;
    private String department;
    private double salary;
    private double performanceRating;
    private int yearsOfExperience;
    private boolean isActive;

    public Employee(T employeeId, String name, String department, double salary,
                    double performanceRating, int yearsOfExperience, boolean isActive) {
        this.employeeId = employeeId;
        this.name = name;
        this.department = department;
        this.salary = salary;
        this.performanceRating = (performanceRating<=5)?performanceRating:5;
        this.yearsOfExperience = yearsOfExperience;
        this.isActive = isActive;
    }

    public T getEmployeeId() { return employeeId; }
    public String getName() { return name; }
    public String getDepartment() { return department; }
    public double getSalary() { return salary; }
    public double getPerformanceRating() { return performanceRating; }
    public int getYearsOfExperience() { return yearsOfExperience; }
    public boolean isActive() { return isActive; }

    public void setName(String name) { this.name = name; }
    public void setDepartment(String department) { this.department = department; }
    public void setSalary(double salary) { this.salary = salary; }
    public void setPerformanceRating(double rating) {this.performanceRating = (rating<=5)?rating:5;}
    public void setYearsOfExperience(int years) { this.yearsOfExperience = years; }
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