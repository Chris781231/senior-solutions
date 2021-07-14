package training360.navreservation.service;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import training360.navreservation.entity.FreeTimeInterval;
import training360.navreservation.Type;
import training360.navreservation.command.CreateReservationCommand;
import training360.navreservation.entity.Reservation;
import training360.navreservation.entity.ReservationDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationService {

    private List<Reservation> reservations = new ArrayList<>(List.of(
            new Reservation("1234567890", "001", LocalDateTime.of(2021,8,1,10,0), LocalDateTime.of(2021,8,1,11,0))
    ));

    private ModelMapper modelMapper;

    public ReservationService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    List<Type> types = List.of(
            new Type("001", "Adóbevallás"),
            new Type("002", "Befizetés"),
            new Type("003", "Korrekció")
    );

    public List<Type> getTypes() {
        return new ArrayList(types);
    }

    public ReservationDTO createReservation(CreateReservationCommand command) {
        FreeTimeInterval interval = new FreeTimeInterval(command.getInterval().getStart(), command.getInterval().getEnd());
        Reservation reservation = new Reservation(command.getTaxID(), command.getCaseNumber(), interval.getStart(), interval.getEnd());
        reservations.add(reservation);

        return modelMapper.map(reservation, ReservationDTO.class);
    }

    public List<ReservationDTO> getReservations() {
        java.lang.reflect.Type targetListType = new TypeToken<List<ReservationDTO>>(){}.getType();
        return modelMapper.map(reservations, targetListType);
    }
}
