package xrddev.practiceportal.controller.practice_office;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import xrddev.practiceportal.service.api.CompanyService;

@Controller
@RequestMapping("/practice-office/companies")
@RequiredArgsConstructor
public class PracticeOfficeCompanyController {

    private final CompanyService companyService;

    @GetMapping()
    public String manageCompanies(Model model) {
        model.addAttribute("companies", companyService.getAllMappedToDashboardDto());
        return "practice_office/companies";
    }

    @PostMapping("/delete/{email}")
    public String deleteCompany(@PathVariable String email) {
        companyService.deleteByEmail(email);
        return "redirect:/practice-office/companies";
    }
}
