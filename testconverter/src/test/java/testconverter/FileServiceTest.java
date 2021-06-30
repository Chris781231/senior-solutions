package testconverter;

import org.junit.jupiter.api.Test;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class FileServiceTest {

    private Path file = Path.of("questions.html");

    @Test
    void readFromFile() {
        FileService service = new FileService();
        service.readFromFile(file);
        System.out.println(service.getQuestions());
        service.saveToFile(Path.of("html.csv"));
    }
}