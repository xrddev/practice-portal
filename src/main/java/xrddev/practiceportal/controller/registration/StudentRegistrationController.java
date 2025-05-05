package xrddev.practiceportal.controller.registration;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import xrddev.practiceportal.config.SessionKeys;
import xrddev.practiceportal.model.Student;
import xrddev.practiceportal.model.enums.Department;
import xrddev.practiceportal.model.enums.Interests;
import xrddev.practiceportal.model.enums.Skills;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/public/register/student")
public class StudentRegistrationController {

    @GetMapping
    public String showStudentRegistrationForm(Model model) {

        List<String> departments = Arrays.stream(Department.values()).map(Enum::name).toList();
        model.addAttribute(SessionKeys.DEPARTMENTS, departments);

        List<String> skills = Arrays.stream(Skills.values()).map(Enum::name).toList();
        model.addAttribute(SessionKeys.SKILLS, skills);

        List<String> interests = Arrays.stream(Interests.values()).map(Enum::name).toList();
        model.addAttribute(SessionKeys.INTERESTS, interests);

        return "register/student_registration";
    }

    @PostMapping
    public String handleStudentRegistration(
            @RequestParam @NotNull @Size(min = 5, max = 7) String studentNumber,
            @RequestParam @NotNull String department,
            @RequestParam @NotNull int yearOfStudy,
            @RequestParam @NotNull double averageGrade,
            @RequestParam List<String> skills,
            @RequestParam List<String> interests,
            @RequestParam(required = false) String preferredLocation,
            Model model)
    {
        Student student = new Student();
        student.setPassword((String) model.getAttribute(SessionKeys.PASSWORD));
        student.setEmail((String) model.getAttribute(SessionKeys.EMAIL));
        student.setStudentNumber(studentNumber);
        student.setDepartment(Department.valueOf(department));
        student.setYearOfStudy(yearOfStudy);
        student.setAverageGrade(averageGrade);
        student.setSkills(skills.stream().map(Skills::valueOf).toList());
        student.setInterests(interests.stream().map(Interests::valueOf).toList());
        student.setPreferredLocation(preferredLocation);

        System.out.println(student);

        return "redirect:/public/register/success";
    }
}

