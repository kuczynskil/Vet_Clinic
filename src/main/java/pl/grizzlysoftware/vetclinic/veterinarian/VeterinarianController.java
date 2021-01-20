package pl.grizzlysoftware.vetclinic.veterinarian;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import pl.grizzlysoftware.vetclinic.appointment.Appointment;
import pl.grizzlysoftware.vetclinic.appointment.AppointmentRepository;

import java.time.LocalDate;
import java.util.List;

@Controller
public class VeterinarianController {

    private final VeterinarianRepository veterinarianRepository;
    private final AppointmentRepository appointmentRepository;

    public VeterinarianController(VeterinarianRepository veterinarianRepository,
                                  AppointmentRepository appointmentRepository) {
        this.veterinarianRepository = veterinarianRepository;
        this.appointmentRepository = appointmentRepository;
    }

    @GetMapping("/vet")
    public String chooseVetToDisplayAppointment(Model model) {
        List<Veterinarian> veterinarians = veterinarianRepository.findAll();
        model.addAttribute("veterinarians", veterinarians);
        model.addAttribute("postActionUrl", "/vet/");
        return "vet-list";
    }

    @GetMapping("/vet/{id}")
    public String veterinariansAppointments(@PathVariable long id,
                                            @RequestParam(required = false) String date,
                                            Model model) {
        Veterinarian veterinarian = veterinarianRepository.getOne(id);
        if (null != date) {
            LocalDate localDate = LocalDate.parse(date);
            List<Appointment> scheduledAppointments =
                    appointmentRepository.findVeterinariansAppointmentsForGivenDay(veterinarian.getId(), localDate);
            model.addAttribute("appointments", scheduledAppointments);
            model.addAttribute("date", date);
        }
        model.addAttribute("vetId", veterinarian.getId());
        return "vet-scheduled-appointments";
    }
}
