package database;

import java.util.*;
import java.util.stream.*;
import models.Employee;
import comparators.*;

public class EmployeeDatabase<T> {
    private Map<T, Employee<T>> employeeMap = new HashMap<>();

    public void addEmployee(Employee<T> employee) {
        employeeMap.put(employee.getEmployeeId(), employee);
    }

    public void removeEmployee(T employeeId) {
        employeeMap.remove(employeeId);
    }

    public void updateEmployeeDetails(T employeeId, String field, Object newValue) {
        Employee<T> emp = employeeMap.get(employeeId);
        if (emp != null) {
            switch (field.toLowerCase()) {
                case "name": emp.setName((String) newValue); break;
                case "department": emp.setDepartment((String) newValue); break;
                case "salary": emp.setSalary((Double) newValue); break;
                case "performance": emp.setPerformanceRating((Double) newValue); break;
                case "experience": emp.setYearsOfExperience((Integer) newValue); break;
                case "status": emp.setActive((Boolean) newValue); break;
            }
        }
    }

    public List<Employee<T>> getAllEmployees() {
        return new ArrayList<>(employeeMap.values());
    }

    public List<Employee<T>> searchByDepartment(String department) {
        return employeeMap.values().stream()
                .filter(e -> e.getDepartment().equalsIgnoreCase(department))
                .collect(Collectors.toList());
    }

    public List<Employee<T>> searchByName(String name) {
        return employeeMap.values().stream()
                .filter(e -> e.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Employee<T>> filterByPerformance(double minRating) {
        return employeeMap.values().stream()
                .filter(e -> e.getPerformanceRating() >= minRating)
                .collect(Collectors.toList());
    }

    public List<Employee<T>> filterBySalaryRange(double min, double max) {
        return employeeMap.values().stream()
                .filter(e -> e.getSalary() >= min && e.getSalary() <= max)
                .collect(Collectors.toList());
    }

    public void displayWithIterator() {
        Iterator<Employee<T>> iterator = employeeMap.values().iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    public List<Employee<T>> sortByExperience() {
        return employeeMap.values().stream()
                .sorted()
                .collect(Collectors.toList());
    }

    public List<Employee<T>> sortBySalary() {
        return employeeMap.values().stream()
                .sorted(new EmployeeSalaryComparator<>())
                .collect(Collectors.toList());
    }

    public List<Employee<T>> sortByPerformance() {
        return employeeMap.values().stream()
                .sorted(new EmployeePerformanceComparator<>())
                .collect(Collectors.toList());
    }

    public void giveSalaryRaiseToTopPerformers(double threshold, double percentage) {
        employeeMap.values().stream()
                .filter(e -> e.getPerformanceRating() >= threshold)
                .forEach(e -> e.setSalary(e.getSalary() * (1 + percentage / 100)));
    }

    public List<Employee<T>> getTopPaidEmployees(int limit) {
        return employeeMap.values().stream()
                .sorted(new EmployeeSalaryComparator<>())
                .limit(limit)
                .collect(Collectors.toList());
    }

    public double getAverageSalaryByDepartment(String department) {
        return employeeMap.values().stream()
                .filter(e -> e.getDepartment().equalsIgnoreCase(department))
                .mapToDouble(Employee::getSalary)
                .average()
                .orElse(0.0);
    }

    public void displayAllEmployees() {
        System.out.printf("%-10s %-20s %-15s %-10s %-10s %-10s %-10s\n",
                "ID", "Name", "Department", "Salary", "Rating", "Experience", "Active");
        employeeMap.values().forEach(System.out::println);
    }
}