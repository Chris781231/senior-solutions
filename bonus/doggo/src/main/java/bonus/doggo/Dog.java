package bonus.doggo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "dogs")
public class Dog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String breed;

    private int age;

    private String favGame;

    public Dog(String name, String breed, int age, String favGame) {
        this.name = name;
        this.breed = breed;
        this.age = age;
        this.favGame = favGame;
    }
}
