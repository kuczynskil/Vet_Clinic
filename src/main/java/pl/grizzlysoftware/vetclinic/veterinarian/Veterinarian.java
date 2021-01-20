package pl.grizzlysoftware.vetclinic.veterinarian;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
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
        List<LocalTime> times = new ArrayList<>(Collections.singletonList(getStartingTime()));
        ZoneId systemsZone = ZoneId.systemDefault();
        ZoneId databaseZone = ZoneId.of("UTC");
        int hoursBetween = (int) ChronoUnit.HOURS.between(LocalTime.now(databaseZone), LocalTime.now(systemsZone));
        int appointmentLengthMinutes = appointmentLength.minusHours(hoursBetween).getHour() * 60 + appointmentLength.getMinute();
        LocalTime lastPossibleAppointmentTime = startingTime.plusHours(workingHours)
                .minusMinutes(appointmentLengthMinutes);

        while (times.get(times.size() - 1).plusMinutes(appointmentLengthMinutes).isBefore(lastPossibleAppointmentTime)
                || times.get(times.size() - 1).plusMinutes(appointmentLengthMinutes).equals(lastPossibleAppointmentTime)) {
            times.add(times.get(times.size() - 1).plusMinutes(appointmentLengthMinutes));
        }
        return times;
    }
}
