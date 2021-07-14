package training360.navreservation.controller;

import org.springframework.web.bind.annotation.*;
import training360.navreservation.service.ReservationService;
import training360.navreservation.Type;
import training360.navreservation.command.CreateReservationCommand;
import training360.navreservation.entity.ReservationDTO;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/")
public class ReservationController {

    private final ReservationService service;

    public ReservationController(ReservationService service) {
        this.service = service;
    }

    @GetMapping("/api/types")
    public List<Type> getTypes() {
        return service.getTypes();
    }

    @GetMapping("/api/appointments")
    public List<ReservationDTO> getReservations() {
        return service.getReservations();
    }

    @PostMapping("/api/appointments")
    public ReservationDTO createReservation(@RequestBody @Valid CreateReservationCommand command) {
        return service.createReservation(command);
    }
}
