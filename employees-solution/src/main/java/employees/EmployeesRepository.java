package employees;

import java.util.List;

public interface EmployeesRepository {

    void save(String name);

    List<Employee> findAll();

    void deleteAll();
}
