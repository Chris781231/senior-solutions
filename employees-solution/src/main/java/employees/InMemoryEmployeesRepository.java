package employees;

import java.util.ArrayList;
import java.util.List;

public class InMemoryEmployeesRepository implements EmployeesRepository {

    private final List<Employee> employees = new ArrayList<>();

    @Override
    public void save(String name) {
        employees.add(new Employee(name));
    }

    @Override
    public List<Employee> findAll() {
        return new ArrayList<>(employees);
    }

    @Override
    public void deleteAll() {
        employees.clear();
    }
}
