package training.bikes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BikeDto {

    private String id;
    private String lastUserId;
    private String lastGetBackTime;
    private double kmOnLastDrive;
}
