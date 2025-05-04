package xrddev.practiceportal.controller.registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import xrddev.practiceportal.service.CompanyService;

@Controller
@RequestMapping("/public/register/company")
public class CompanyRegistrationController {

    private final CompanyService companyService;

    @Autowired
    public CompanyRegistrationController(CompanyService companyService) {
        this.companyService = companyService;
    }

    // Εμφάνιση φόρμας εγγραφής εταιρείας
    @GetMapping
    public String showCompanyRegistrationForm() {
        return "register/company_registration"; // Επιστροφή του HTML template
    }


    @PostMapping
    public ResponseEntity<String> registerCompany(
            @RequestParam String companyName,
            @RequestParam String address,
            @RequestParam String phone,
            @RequestParam(required = false) String website,
            @RequestParam String internshipCoordinator,
            @RequestParam String internshipCoordinatorEmail) {

        // Εκτυπώσεις δεδομένων για δοκιμαστικούς σκοπούς
        System.out.println("Company Name: " + companyName);
        System.out.println("Address: " + address);
        System.out.println("Phone: " + phone);
        System.out.println("Website: " + (website != null ? website : "N/A"));
        System.out.println("Internship Coordinator: " + internshipCoordinator);
        System.out.println("Internship Coordinator Email: " + internshipCoordinatorEmail);

        // Επιστροφή επιτυχούς μηνύματος
        return ResponseEntity.ok("Company data received and printed successfully!");
    }


}