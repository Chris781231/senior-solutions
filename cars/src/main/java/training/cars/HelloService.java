package training.cars;

import org.springframework.stereotype.Service;

@Service
public class HelloService {

    public String welcomePage() {
        return "Welcome on page!";
    }
}
