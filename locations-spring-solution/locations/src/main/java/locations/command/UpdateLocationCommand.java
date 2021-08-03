package locations.command;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateLocationCommand {

    @Schema(description = "name of location", example = "Nézsa")
    @NotBlank
    private String name;

    @Schema(description = "latitude of location", example = "47.010205")
    @Min(-90)
    @Max(90)
    private double lat;

    @Schema(description = "longitude of location", example = "19.141531")
    @Min(-180)
    @Max(180)
    private double lon;
}
