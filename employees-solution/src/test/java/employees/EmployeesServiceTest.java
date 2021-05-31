package employees;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EmployeesServiceTest {

    private final EmployeesService employeesService = new EmployeesService(new MariaDbEmployeesRepository());

    @BeforeEach
    void init() {
        employeesService.deleteAll();
    }

    @Test
    void saveThanListTest() {
        employeesService.save("John Doe");

        List<Employee> employees = employeesService.listEmployees();
        assertEquals(1, employees.size());
        assertEquals("John Doe", employees.get(0).getName());
    }


    @Test
    void saveTwoThanListTest() {
        employeesService.save("John Doe");
        employeesService.save("Jane Doe");

        List<Employee> employees = employeesService.listEmployees();
        assertEquals(2, employees.size());
        assertEquals("Jane Doe, John Doe", employees.stream().map(Employee::getName).collect(Collectors.joining(", ")));
    }
}