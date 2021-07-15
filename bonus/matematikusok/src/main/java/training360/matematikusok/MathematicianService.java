package training360.matematikusok;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class MathematicianService {

    private List<Mathematician> mathematicians = new ArrayList<>(List.of(
            new Mathematician("John Doe", new ArrayList<>(List.of("Geometry")), LocalDate.of(1978, 1, 2), 91)
    ));

    public List<Mathematician> getMathematicians() {
        return new ArrayList<>(mathematicians);
    }

    public Mathematician saveMathematician(Mathematician mathematician) {
        mathematicians.add(mathematician);
        return mathematician;
    }

    public void deleteAll() {
        mathematicians.clear();
    }
}
