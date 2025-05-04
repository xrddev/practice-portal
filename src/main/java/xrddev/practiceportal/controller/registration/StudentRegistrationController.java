package xrddev.practiceportal.controller.registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import xrddev.practiceportal.model.Student;
import xrddev.practiceportal.model.User;
import xrddev.practiceportal.model.enums.Department;
import xrddev.practiceportal.model.enums.Interests;
import xrddev.practiceportal.model.enums.Skills;
import xrddev.practiceportal.repository.StudentRepository;
import xrddev.practiceportal.repository.UserRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/public/register/student")
public class StudentRegistrationController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping
    public String showStudentRegistrationForm(@RequestParam("userId") Long userId, Model model) {
        model.addAttribute("userId", userId); // hidden πεδίο στο form
        return "register/student_registration";
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<String> registerStudent(
            @RequestParam Long userId,
            @RequestParam String studentNumber,
            @RequestParam String department,
            @RequestParam int yearOfStudy,
            @RequestParam double averageGrade,
            @RequestParam(required = false) String skills,
            @RequestParam(required = false) String interests,
            @RequestParam(required = false) String preferredLocation) {

        System.out.println("Received data for Student Registration:");
        System.out.println("UserId: " + userId);

        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            System.out.println("[ERROR] User not found with ID: " + userId);
            return ResponseEntity.badRequest().body("User not found.");
        }

        return ResponseEntity.ok("Student data saved successfully!");
    }
}
