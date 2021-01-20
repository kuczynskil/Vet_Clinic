package pl.grizzlysoftware.vetclinic.appointment;

import lombok.Getter;
import lombok.Setter;
import pl.grizzlysoftware.vetclinic.appuser.AppUser;
import pl.grizzlysoftware.vetclinic.veterinarian.Veterinarian;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private AppUser appUser;
    @ManyToOne
    private Veterinarian veterinarian;

    private LocalDateTime dateAndTime;

    public Appointment(AppUser appUser, Veterinarian veterinarian, LocalDateTime dateAndTime) {
        this.appUser = appUser;
        this.veterinarian = veterinarian;
        this.dateAndTime = dateAndTime;
    }

    public Appointment() {

    }
}
