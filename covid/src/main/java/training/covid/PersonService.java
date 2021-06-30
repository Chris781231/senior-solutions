package training.covid;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.internal.bytebuddy.description.method.MethodDescription;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class PersonService {

    private ModelMapper modelMapper;
    private AtomicLong atomicLong = new AtomicLong();

    private List<Person> personList = new ArrayList<>(List.of(
            new Person(atomicLong.incrementAndGet(), "Balogh Béla", "123456788", LocalDate.of(1970, 3, 5), "Budapest", "Széchenyi 4"),
            new Person(atomicLong.incrementAndGet(), "John Doe", "123456712", LocalDate.of(1980, 3, 5), "Budapest", "Széchenyi 4")
    ));

    public PersonService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public PersonDto findBySid(String sid) {
        return modelMapper.map(personList.stream()
                .filter(p -> p.getSid().equals(sid))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Cannot find person with this sid: " + sid)), PersonDto.class);
    }

    public PersonDto registration(CreatePersonCommand command) {
        Person person = new Person(atomicLong.incrementAndGet(), command.getName(), command.getSid(), command.getBirthOfDate(), command.getCity(), command.getAddress());

        personList.add(person);
        return modelMapper.map(person, PersonDto.class);
    }

    public List<PersonDto> getPersonList() {
        Type type = new TypeToken<List<PersonDto>>(){}.getType();
        return modelMapper.map(personList, type);
    }

    public void deleteAll() {
        atomicLong = new AtomicLong();
        personList.clear();
    }

    public static void main(String[] args) {
        PersonService service = new PersonService(new ModelMapper());
        service.getPersonList();
    }
}
