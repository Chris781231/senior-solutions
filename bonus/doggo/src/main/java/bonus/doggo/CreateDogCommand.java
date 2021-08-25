package bonus.doggo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateDogCommand {

    private String name;

    private String breed;

    private int age;

    private String favGame;
}
