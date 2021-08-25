package bonus.doggo;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DogService {

    private final ModelMapper modelMapper;

    private final DogRepository repo;

    public DogDto save(CreateDogCommand command) {
        Dog dog = new Dog(command.getName(), command.getBreed(), command.getAge(), command.getFavGame());
        repo.save(dog);
        return modelMapper.map(dog, DogDto.class);
    }

    public List<DogDto> listDogs(Optional<String> breed) {
        List<Dog> dogs;
        if (breed.isPresent()) {
            dogs = repo.findDogsByBreed(breed.get());
        } else {
            dogs = repo.findAll();
        }
        Type targetType = new TypeToken<List<DogDto>>(){}.getType();
        return modelMapper.map(dogs, targetType);
    }

    public DogDto findById(long id) {
        Dog dog = repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(String.format("Nincs kutya %d id-vel", id)));
        return modelMapper.map(dog, DogDto.class);
    }
}
