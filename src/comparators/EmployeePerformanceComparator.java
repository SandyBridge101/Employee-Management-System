package comparators;

import models.Employee;

import java.util.*;

public class EmployeePerformanceComparator<T> implements Comparator<Employee<T>> {
    public int compare(Employee<T> e1, Employee<T> e2) {
        return Double.compare(e2.getPerformanceRating(), e1.getPerformanceRating());
    }
}