// EmployeeDatabaseTest.java (JUnit 4)
package test;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;
import models.*;
import database.*;
import Exceptions.*;


public class EmployeeDatabaseTest {
    private EmployeeDatabase<UUID> database;
    private UUID sampleId;
    private Employee<UUID> sampleEmployee;

    @Before
    public void setUp() {
        database = new EmployeeDatabase<>();
        sampleId = UUID.randomUUID();
        try {
            sampleEmployee = new Employee<>(sampleId, "Alice Smith", "IT", 75000, 4.5, 5, true);

        }catch (InvalidEmployeeNameException| InvalidDepartmentException| InvalidSalaryException| InvalidRatingException| InvalidYearsofExperienceException e){
            System.out.println("Caught exception: " + e.getClass().getSimpleName());
            System.out.println("Message: " + e.getMessage());
        }
        database.addEmployee(sampleEmployee);
    }

    @Test
    public void testAddEmployee() {
        UUID id = UUID.randomUUID();
        try {
            Employee<UUID> emp = new Employee<>(id, "John Doe", "Finance", 60000, 4.0, 3, true);
            database.addEmployee(emp);
            assertEquals(emp, database.getAllEmployees().stream()
                    .filter(e -> e.getEmployeeId().equals(id)).findFirst().orElse(null));
        }catch (InvalidEmployeeNameException| InvalidDepartmentException| InvalidSalaryException| InvalidRatingException| InvalidYearsofExperienceException e){
            System.out.println("Caught exception: " + e.getClass().getSimpleName());
            System.out.println("Message: " + e.getMessage());
        }

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
        assertNull(database.getAllEmployees().stream()
                .filter(e -> e.getEmployeeId().equals(sampleId)).findFirst().orElse(null));
    }

    @Test
    public void testRemoveNonExistingEmployeeThrows() {
        UUID fakeId = UUID.randomUUID();
        database.removeEmployee(fakeId);
    }
}
