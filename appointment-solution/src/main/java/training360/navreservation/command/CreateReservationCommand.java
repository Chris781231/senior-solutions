package training360.navreservation.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import training360.navreservation.validation.CDV;
import training360.navreservation.validation.CaseNumber;
import training360.navreservation.entity.FreeTimeInterval;
import training360.navreservation.validation.TimeInterval;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateReservationCommand {

    @CDV
    private String taxID;

    @CaseNumber
    private String caseNumber;

    @TimeInterval
    private FreeTimeInterval interval;
}
