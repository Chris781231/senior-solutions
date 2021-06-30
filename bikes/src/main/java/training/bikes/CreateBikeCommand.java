package training.bikes;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateBikeCommand {

    private String id;
    private String lastUserId;
    private String lastGetBackTime;
    private double kmOnLastDrive;
}
