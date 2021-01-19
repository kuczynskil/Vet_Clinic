package pl.grizzlysoftware.vetclinic.appointment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.grizzlysoftware.vetclinic.appuser.AppUser;
import pl.grizzlysoftware.vetclinic.veterinarian.Veterinarian;

import java.time.LocalDate;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    @Query(value = "SELECT * FROM appointment WHERE veterinarian_id = ?1 AND DATE(date_and_time) = ?2",
            nativeQuery = true)
    List<Appointment> findVeterinariansAppointmentsForGivenDay(long vetId, LocalDate date);

    List<Appointment> findAllByAppUser(AppUser appUser);
    List<Appointment> findAllByVeterinarian(Veterinarian veterinarian);
}
