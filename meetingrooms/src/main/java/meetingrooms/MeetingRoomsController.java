package meetingrooms;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class MeetingRoomsController {

    private final Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8)
            .useLocale(new Locale("hu", "HU"));

//    private final MeetingRoomsServices services = new MeetingRoomsServices(new InMemoryMeetingRoomsRepository());

    private final MeetingRoomsServices services = new MeetingRoomsServices(new MariaDbMeetingRoomsRepository());

    public static void main(String[] args) {
        new MeetingRoomsController().start();
    }

    private void start() {
        printMenu();
        processMenuItem();
    }

    private void printMenu() {
        System.out.println("""
                0. Tárgyaló rögzítése
                1. Tárgyalók névsorrendben
                2. Tárgyalók név alapján visszafele sorrendben
                3. Minden második tárgyaló
                4. Területek
                5. Keresés pontos név alapján
                6. Keresés névtöredék alapján
                7. Keresés terület alapján
                9. Alkalmazás bezárása""");
    }

    private void processMenuItem() {
        String choice;
        while (!(choice = scanner.nextLine()).equals("9")) {
            switch (choice) {
                case "0" -> save();
                case "1" -> listAllByName();
                case "2" -> listAllByNameReversed();
                case "3" -> listEverySecond();
                case "4" -> listAreas();
                case "5" -> findByName();
                case "6" -> findByNamePart();
                case "7" -> findByAreaLargerThan();
            }
            printMenu();
        }
    }

    private void save() {
        System.out.println("""
                TÁRGYALÓ RÖGZÍTÉSE
                ------------------
                """);
        System.out.print("Név: ");
        String name = scanner.nextLine();
        System.out.print("Szélesség (méter): ");
        String width = scanner.nextLine();
        System.out.print("Hosszúság (méter): ");
        String length = scanner.nextLine();
        // TODO: 2021. 06. 03. Kivételkezelés Integer.parseInt()
        services.save(name, Integer.parseInt(width), Integer.parseInt(length));
        System.out.println("Tárgyaló rögzítve.\n");
    }

    private void listAllByName() {
        System.out.println("""
                TÁRGYALÓK LISTÁZÁSA
                -------------------
                """);
        List<String> result = services.listAllByName();
        System.out.println(result + "\n");
    }

    private void listAllByNameReversed() {
        System.out.println("""
                TÁRGYALÓ LISTÁZÁSA (VISSZAFELÉ)
                -------------------------------
                """);
        List<String> result = services.listAllByNameReversed();
        System.out.println(result + "\n");
    }

    private void listEverySecond() {
        System.out.println("""
                TÁRGYALÓ LISTÁZÁSA (MINDEN MÁSODIK)
                -----------------------------------
                """);
        List<String> result = services.listEverySecond();
        System.out.println(result + "\n");
    }

    private void listAreas() {
        System.out.println("""
                TÁRGYALÓ LISTÁZÁSA (TERÜLETEK)
                ------------------------------
                """);
        List<MeetingRoom> result = services.listAreas();
        System.out.println(result + "\n");
    }

    private void findByName() {
        System.out.println("""
                TÁRGYALÓ KERESÉSE (NÉV SZERINT)
                ------------------
                """);
        System.out.print("Név: ");
        String name = scanner.nextLine();
        MeetingRoom result = services.findByName(name);
        System.out.println(result + "\n");
    }

    private void findByNamePart() {
        System.out.println("""
                TÁRGYALÓ KERESÉSE (NÉV RÉSZLET SZERINT)
                ---------------------------------------
                """);
        System.out.print("Részlet: ");
        String namePart = scanner.nextLine();
        List<MeetingRoom> result = services.findByNamePart(namePart);
        System.out.println(result + "\n");
    }

    private void findByAreaLargerThan() {
        System.out.println("""
                TÁRGYALÓ KERESÉSE (TERÜLET SZERINT)
                -----------------------------------
                """);
        System.out.print("Terület (m2): ");
        String area = scanner.nextLine();
        List<MeetingRoom> result = services.findByAreaLargerThan(Integer.parseInt(area));
        System.out.println(result + "\n");
    }
}
