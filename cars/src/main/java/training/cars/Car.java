package training.cars;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Car {

    private String mark;
    private String type;
    private int age;
    private Status status;

    private List<KmState> kmStates;
}
