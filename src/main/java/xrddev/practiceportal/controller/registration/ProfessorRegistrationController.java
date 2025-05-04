package xrddev.practiceportal.controller.registration;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/public/register/professor")
public class ProfessorRegistrationController {

    @GetMapping
    public String showProfessorRegistrationForm() {
        return "register/professor_registration";
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<String> registerProfessor(
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam List<String> interests) {

        // Εκτύπωση των δεδομένων για δοκιμαστικούς σκοπούς
        System.out.println("First Name: " + firstName);
        System.out.println("Last Name: " + lastName);
        System.out.println("Interests: " + String.join(", ", interests));

        // Επιστροφή επιτυχούς μηνύματος
        return ResponseEntity.ok("Professor data received and printed successfully!");
    }
}