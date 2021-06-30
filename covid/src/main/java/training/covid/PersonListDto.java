package training.covid;

import lombok.Data;

import java.util.List;

@Data
public class PersonListDto {

    private List<PersonDto> personDtos;

    public PersonListDto(List<PersonDto> personDtos) {
        this.personDtos = personDtos;
    }
}
