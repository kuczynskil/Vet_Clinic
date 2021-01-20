package pl.grizzlysoftware.vetclinic.appuser;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.grizzlysoftware.vetclinic.appointment.Appointment;
import pl.grizzlysoftware.vetclinic.appointment.AppointmentRepository;
import pl.grizzlysoftware.vetclinic.veterinarian.VeterinarianRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Controller
public class AppUserController {

    private final VeterinarianRepository veterinarianRepository;
    private final AppointmentRepository appointmentRepository;
    private final AppUserRepository appUserRepository;

    public AppUserController(VeterinarianRepository veterinarianRepository,
                             AppointmentRepository appointmentRepository,
                             AppUserRepository appUserRepository) {
        this.veterinarianRepository = veterinarianRepository;
        this.appointmentRepository = appointmentRepository;
        this.appUserRepository = appUserRepository;
    }

    @GetMapping("")
    public String homepage() {
        return "home";
    }

    @PostMapping("/user/authenticate")
    public String authenticateUser(@RequestParam String loginId,
                                   @RequestParam String loginPIN,
                                   @RequestParam String appointmentDateString,
                                   @RequestParam String appointmentTimeString,
                                   @RequestParam long vetId,
                                   Model model) {
        AppUser appUser = appUserRepository.findByLoginID(loginId);
        if (!authenticate(appUser, model, loginPIN)) {
            model.addAttribute("appointmentDateString", appointmentDateString);
            model.addAttribute("appointmentTimeString", appointmentTimeString);
            model.addAttribute("vetId", vetId);
            return "appointment-make-authentication";
        }
        LocalDate appointmentDate = LocalDate.parse(appointmentDateString);
        LocalTime appointmentTime = LocalTime.parse(appointmentTimeString);
        Appointment appointment = new Appointment(appUser, veterinarianRepository.getOne(vetId),
                LocalDateTime.of(appointmentDate, appointmentTime));
        appointmentRepository.save(appointment);
        return "appointment-make-confirm";
    }

    public boolean authenticate(AppUser appUser, Model model, String loginPIN) {
        if (null == appUser || !loginPIN.equals(appUser.getLoginPIN())) {
            model.addAttribute("invalidIDOrPINMessage", "Invalid user's ID or PIN.");
            return false;
        }
        return true;
    }

    @GetMapping("/appointment/cancel")
    public String cancelAnAppointment() {
        return "appointment-cancel-authentication";
    }

    @PostMapping("/appointment/cancel")
    public String authenticateUserForCancellingAppointments(@RequestParam String loginId,
                                                            @RequestParam String loginPIN,
                                                            Model model) {
        AppUser appUser = appUserRepository.findByLoginID(loginId);
        if (!authenticate(appUser, model, loginPIN)) {
            return "appointment-cancel-authentication";
        }
        return "redirect:/appointment/cancel/user/" + appUser.getId();
    }

    @GetMapping("/appointment/cancel/user/{id}")
    public String chooseAppointmentToBeCancelled(@PathVariable long id, Model model) {
        AppUser appUser = appUserRepository.getOne(id);
        List<Appointment> appUsersAppointments = appointmentRepository.findAllByAppUser(appUser);
        model.addAttribute("appointments", appUsersAppointments);
        return "appointment-cancel";
    }

    @GetMapping("/appointment/cancel/{id}")
    public String cancelConfirm(@PathVariable long id, Model model) {
        model.addAttribute("appointmentId", id);
        model.addAttribute("appUserId", appointmentRepository.getOne(id).getAppUser().getId());
        return "appointment-cancel-confirm";
    }

    @GetMapping("appointment/cancel/{id}/perform")
    public String cancelPerform(@PathVariable long id) {
        long appUsersId = appointmentRepository.getOne(id).getAppUser().getId();
        appointmentRepository.delete(appointmentRepository.getOne(id));
        return "redirect:/appointment/cancel/user/" + appUsersId;
    }

}
