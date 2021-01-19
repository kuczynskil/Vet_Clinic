package pl.grizzlysoftware.vetclinic.veterinarian;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Getter
public class Veterinarian {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String surname;
    private String specialty;
    private LocalTime startingTime;
    private LocalTime appointmentLength;
    private byte workingHours;

    public List<LocalTime> possibleAppointmentTimesInADay() {
        List<LocalTime> availableAppointmentsTimes = new ArrayList<>
                (Collections.singletonList(getStartingTime()));
        while (availableAppointmentsTimes.get(availableAppointmentsTimes.size() - 1).isBefore(startingTime.plusHours(workingHours).minusMinutes(appointmentLength.getHour() * 60 + appointmentLength.getMinute()))) {
            availableAppointmentsTimes.add(availableAppointmentsTimes.get(availableAppointmentsTimes.size() - 1).plusMinutes(appointmentLength.getHour() * 60 + appointmentLength.getMinute()));
        }

        return availableAppointmentsTimes;
    }
}
