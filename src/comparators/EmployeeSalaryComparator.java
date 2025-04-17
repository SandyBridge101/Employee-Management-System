package comparators;

import models.Employee;

import java.util.*;

public class EmployeeSalaryComparator<T> implements Comparator<Employee<T>> {
    public int compare(Employee<T> e1, Employee<T> e2) {
        return Double.compare(e2.getSalary(), e1.getSalary());
    }
}