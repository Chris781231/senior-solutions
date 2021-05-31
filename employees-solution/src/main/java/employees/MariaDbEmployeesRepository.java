package employees;

import org.flywaydb.core.Flyway;
import org.mariadb.jdbc.MariaDbDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;
import java.util.List;

public class MariaDbEmployeesRepository implements EmployeesRepository {

    private final JdbcTemplate jdbcTemplate;

    public MariaDbEmployeesRepository() {
        try {
            MariaDbDataSource dataSource = new MariaDbDataSource();
            dataSource.setUrl("jdbc:mariadb://localhost:3308/employees?useUnicode=true");
            dataSource.setUser("employees");
            dataSource.setPassword("employees");

            Flyway flyway = Flyway.configure().dataSource(dataSource).load();
            flyway.migrate();

            this.jdbcTemplate = new JdbcTemplate(dataSource);
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot create dataSource", e);
        }
    }

    @Override
    public void save(String name) {
        jdbcTemplate.update("INSERT INTO employees(emp_name) VALUES(?)", name);
    }

    @Override
    public List<Employee> findAll() {
        return jdbcTemplate.query("SELECT `id`, `emp_name` FROM `employees` ORDER BY `emp_name`",
                (rs, i) -> new Employee(rs.getLong("id"), rs.getString("emp_name")));
    }

    @Override
    public void deleteAll() {
        jdbcTemplate.update("DELETE FROM `employees`");
    }
}
