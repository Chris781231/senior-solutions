package employees;

import java.util.List;

public class EmployeesService {

    private final EmployeesRepository employeesRepository;

    public EmployeesService(EmployeesRepository employeesRepository) {
        this.employeesRepository = employeesRepository;
    }

    public void save(String name) {
        employeesRepository.save(name);
    }

    public List<Employee> listEmployees() {
        return employeesRepository.findAll();
    }

    public void deleteAll() {
        employeesRepository.deleteAll();
    }
}
