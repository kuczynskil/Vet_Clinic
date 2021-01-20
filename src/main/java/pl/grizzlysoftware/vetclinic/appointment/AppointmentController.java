package pl.grizzlysoftware.vetclinic.appointment;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.grizzlysoftware.vetclinic.veterinarian.Veterinarian;
import pl.grizzlysoftware.vetclinic.veterinarian.VeterinarianRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AppointmentController {

    private final VeterinarianRepository veterinarianRepository;
    private final AppointmentRepository appointmentRepository;
    private static final String VET_ID = "vetId";
    private static final String APPOINTMENT_DATE = "appointmentDateString";

    public AppointmentController(VeterinarianRepository veterinarianRepository,
                                 AppointmentRepository appointmentRepository) {
        this.veterinarianRepository = veterinarianRepository;
        this.appointmentRepository = appointmentRepository;
    }

    @GetMapping("/appointment/make")
    public String chooseVeterinarian(Model model) {
        List<Veterinarian> veterinarians = veterinarianRepository.findAll();
        model.addAttribute("veterinarians", veterinarians);
        model.addAttribute("postActionUrl", "/appointment/make/veterinarian/");
        return "vet-list";
    }

    @PostMapping("/appointment/make/veterinarian/date")
    public String chooseDateForAppointment(@RequestParam String appointmentDateString,
                                           @RequestParam long id,
                                           RedirectAttributes redirectAttributes,
                                           Model model) {
        LocalDate chosenDate = LocalDate.parse(appointmentDateString);
        if (chosenDate.getDayOfWeek().toString().equals("SUNDAY") ||
                chosenDate.getDayOfWeek().toString().equals("SATURDAY")) {
            model.addAttribute("weekendDateMessage", "Please choose a date from monday to friday.");
            model.addAttribute(VET_ID, id);
            return "appointment-make-choose-date-and-time";
        }
        redirectAttributes.addFlashAttribute(APPOINTMENT_DATE, appointmentDateString);
        return "redirect:/appointment/make/veterinarian/" + id;
    }

    @GetMapping("/appointment/make/veterinarian/{id}")
    public String chooseTimeForAppointment(@PathVariable long id,
                                           Model model) {
        Veterinarian veterinarian = veterinarianRepository.getOne(id);
        String appointmentDateString = (String) model.asMap().get(APPOINTMENT_DATE);
        if (null != appointmentDateString) {
            List<LocalTime> availableTimesForNewAppointment =
                    availableAppointmentTimes(veterinarian, LocalDate.parse(appointmentDateString));
            model.addAttribute("availableTimes", availableTimesForNewAppointment);
        }
        model.addAttribute(VET_ID, veterinarian.getId());
        return "appointment-make-choose-date-and-time";
    }

    @PostMapping("/appointment/make/veterinarian/{id}")
    public String makeAnAppointment(@PathVariable long id, @RequestParam String appointmentDateString,
                                    @RequestParam String appointmentTimeString, Model model) {
        model.addAttribute(VET_ID, id);
        model.addAttribute(APPOINTMENT_DATE, appointmentDateString);
        model.addAttribute("appointmentTimeString", appointmentTimeString);
        return "appointment-make-authentication";
    }

    public List<LocalTime> availableAppointmentTimes(Veterinarian vet, LocalDate date) {
        List<LocalTime> possibleAppointmentTimes = vet.possibleAppointmentTimesInADay();
        List<LocalTime> scheduledAppointmentTimesForGivenDay = new ArrayList<>();
        List<Appointment> veterinariansAppointmentsForGivenDay =
                appointmentRepository.findVeterinariansAppointmentsForGivenDay(vet.getId(), date);
        if (veterinariansAppointmentsForGivenDay.isEmpty()) {
            return possibleAppointmentTimes;
        }
        for (Appointment appointment : veterinariansAppointmentsForGivenDay) {
            scheduledAppointmentTimesForGivenDay.add(appointment.getDateAndTime().toLocalTime());
        }
        possibleAppointmentTimes.removeIf(scheduledAppointmentTimesForGivenDay::contains);
        return possibleAppointmentTimes;
    }
}
