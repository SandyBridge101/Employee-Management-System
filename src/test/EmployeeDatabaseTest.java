package test;

import Exceptions.*;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

import database.*;
import models.*;

public class EmployeeDatabaseTest {
    private EmployeeDatabase<UUID> database;
    private UUID sampleId;
    private Employee<UUID> sampleEmployee;
    private Employee<UUID> emp;

    @Before
    public void setUp() throws InvalidDepartmentException, InvalidYearsofExperienceException, InvalidSalaryException, InvalidRatingException, InvalidEmployeeNameException {
        database = new EmployeeDatabase<>();
        sampleId = UUID.randomUUID();
        sampleEmployee = new Employee<>(sampleId, "Alice Smith", "IT", 75000, 4.5, 5, true);
        database.addEmployee(sampleEmployee);
    }
    @Test
    public void testAddEmployeeException() throws InvalidDepartmentException, InvalidYearsofExperienceException, InvalidSalaryException, InvalidRatingException, InvalidEmployeeNameException {
        UUID id = UUID.randomUUID();
        assertThrows(InvalidEmployeeNameException.class, () -> database.addEmployee(new Employee<>(id, "", "Finance", 60000, 4.0, 3, true)));

    }

    @Test
    public void testAddEmployee() throws InvalidDepartmentException, InvalidYearsofExperienceException, InvalidSalaryException, InvalidRatingException, InvalidEmployeeNameException {
        UUID id = UUID.randomUUID();
        emp = new Employee<>(id, "John Doe", "Finance", 60000, 4.0, 3, true);
        database.addEmployee(emp);
        Employee<UUID> result = database.getAllEmployees().stream()
                .filter(e -> e.getEmployeeId().equals(id))
                .findFirst()
                .orElse(null);
        assertEquals(emp, result);
    }

    @Test
    public void testSetEmployee() throws InvalidDepartmentException, InvalidYearsofExperienceException, InvalidSalaryException, InvalidRatingException, InvalidEmployeeNameException {
        UUID id = UUID.randomUUID();
        emp = new Employee<>(id, "John Doe", "Finance", 60000, 4.0, 3, true);
        database.addEmployee(emp);
        database.updateEmployeeDetails(emp.getEmployeeId(),"department","IT");
        database.updateEmployeeDetails(emp.getEmployeeId(),"name","Max");

        Employee<UUID> result = database.getAllEmployees().stream()
                .filter(e -> e.getEmployeeId().equals(emp.getEmployeeId()))
                .findFirst()
                .orElse(null);

        assertEquals(emp,result);
    }

    @Test
    public void testSearchByDepartment() {
        List<Employee<UUID>> itEmployees = database.searchByDepartment("IT");
        assertNotNull(itEmployees);
        assertEquals(1, itEmployees.size());
        assertEquals("Alice Smith", itEmployees.get(0).getName());
    }

    @Test
    public void testRemoveEmployee() {
        database.removeEmployee(sampleId);
        assertEquals(0, database.getAllEmployees().size());
        Employee<UUID> result = database.getAllEmployees().stream()
                .filter(e -> e.getEmployeeId().equals(sampleId))
                .findFirst()
                .orElse(null);
        assertNull(result);
    }

    @Test
    public void testRemoveNonExistingEmployeeThrows() throws InvalidDepartmentException, InvalidYearsofExperienceException, InvalidSalaryException, InvalidRatingException, InvalidEmployeeNameException {
        UUID id = UUID.randomUUID();
        emp = new Employee<>(id, "John Doe", "Finance", 60000, 4.0, 3, true);
        database.addEmployee(emp);
        try {
            database.removeEmployee(id);
            database.getAllEmployees().stream()
                    .filter(e -> e.getEmployeeId().equals(id))
                    .findFirst()
                    .orElse(null);
        } catch (NullPointerException e) {
            assertNotNull(e.getMessage()); // Optional: check that the exception has a message
        }
    }
}
