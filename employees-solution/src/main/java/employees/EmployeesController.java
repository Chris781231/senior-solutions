package employees;

import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.Scanner;

public class EmployeesController {

    private final Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8)
            .useLocale(new Locale("hu", "HU"));

//    private EmployeesService employeesService = new EmployeesService(new InMemoryEmployeesRepository());

    private final EmployeesService employeesService = new EmployeesService(new MariaDbEmployeesRepository());

    public static void main(String[] args) {
        new EmployeesController().start();
    }

    private void start() {
        System.out.println("Kérek 5 nevet ([Enter]-rel elválasztva):");

        for (int i = 0; i < 5; i++) {
            String name = scanner.nextLine();
            employeesService.save(name);
        }

        System.out.println(employeesService.listEmployees());
    }
}
