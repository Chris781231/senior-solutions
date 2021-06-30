package training.bikes;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class BikeService {

    private List<Bike> bikes = new ArrayList<>();

    private ModelMapper modelMapper;

    public BikeService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public void addBike(Bike bike) {
        bikes.add(bike);
    }

    public void readFile() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(BikeService.class.getResourceAsStream("/bikes.csv"))))) {
            String line;
            while ((line = reader.readLine()) != null) {
                processLine(line);
            }
        } catch (IOException e) {
            throw new IllegalArgumentException("Cannot read file", e);
        }
    }

    private void processLine(String line) {
        String[] parts = line.split(";");
        bikes.add(new Bike(parts[0], parts[1], parts[2], Double.parseDouble(parts[3])));
    }

    public List<BikeDto> getBikes() {
        if (bikes.isEmpty()) {
            readFile();
        }
        return bikes.stream()
                .map(entity -> modelMapper.map(entity, BikeDto.class))
                .toList();
    }

    public List<String> getUserIds() {
        return bikes.stream()
                .map(bike -> bike.getLastUserId())
                .distinct()
                .toList();
    }
}
