package main;

import Exceptions.*;
import database.EmployeeDatabase;
import models.Employee;

import java.util.UUID;

public class Test {
    public static void main(String[] args) {
        EmployeeDatabase<UUID> db = new EmployeeDatabase<>();
        try {
            db.addEmployee(new Employee<>(UUID.randomUUID(), "Alice Johnson", "IT", 80000, 4.6, -6, true));
            db.addEmployee(new Employee<>(UUID.randomUUID(), "Bob Smith", "Finance", -95000, 4.3, 8, true));

        }catch (InvalidEmployeeNameException| InvalidDepartmentException| InvalidSalaryException| InvalidRatingException| InvalidYearsofExperienceException e){
            System.out.println("Caught exception: " + e.getClass().getSimpleName());
            System.out.println("Message: " + e.getMessage());
        }
        //db.addEmployee(new Employee<>(UUID.randomUUID(), "Clara Lee", "HR", 72000, 3.9, 4, true));
        //db.addEmployee(new Employee<>(UUID.randomUUID(), "David Brown", "IT", 105000, 4.9, 10, true));

        db.displayAllEmployees();
        System.out.println("\nTop Performers:");
        db.filterByPerformance(4.0).forEach(System.out::println);

        System.out.println("\nSorted by Experience:");
        db.sortByExperience().forEach(System.out::println);

        System.out.println("\nTop 3 Salaries:");
        db.getTopPaidEmployees(3).forEach(System.out::println);

        System.out.println("\nAverage Salary in IT: " + db.getAverageSalaryByDepartment("IT"));

        db.giveSalaryRaiseToTopPerformers(4.5, 10);
        System.out.println("\nAfter Salary Raise:");
        db.displayAllEmployees();

    }
}
