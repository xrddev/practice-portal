package xrddev.practiceportal.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@Controller
@RequestMapping("/public/register/student")
public class StudentRegistrationController {

    @GetMapping
    public String showStudentRegistrationForm() {
        return "register/student_registration";
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<String> registerStudent(
            @RequestParam String studentNumber,
            @RequestParam String department,
            @RequestParam int yearOfStudy,
            @RequestParam double averageGrade,
            @RequestParam(required = false) String skills,
            @RequestParam(required = false) String interests,
            @RequestParam(required = false) String preferredLocation) {

        // Εκτυπώσεις δεδομένων για δοκιμαστικούς σκοπούς
        System.out.println("Student Number: " + studentNumber);
        System.out.println("Department: " + department);
        System.out.println("Year of Study: " + yearOfStudy);
        System.out.println("Average Grade: " + averageGrade);
        System.out.println("Skills: " + (skills != null ? skills : "N/A"));
        System.out.println("Interests: " + (interests != null ? interests : "N/A"));
        System.out.println("Preferred Location: " + (preferredLocation != null ? preferredLocation : "N/A"));

        // Επιστροφή επιτυχούς μηνύματος
        return ResponseEntity.ok("Student data received and printed successfully!");
    }
}