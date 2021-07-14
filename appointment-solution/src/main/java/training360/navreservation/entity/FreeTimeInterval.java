package training360.navreservation.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FreeTimeInterval {

    private LocalDateTime start;

    private LocalDateTime end;
}
