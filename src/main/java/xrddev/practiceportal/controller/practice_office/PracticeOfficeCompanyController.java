package xrddev.practiceportal.controller.practice_office;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import xrddev.practiceportal.config.ModelAttributeKeys;
import xrddev.practiceportal.dto.company.CompanyDashboardDto;
import xrddev.practiceportal.service.api.CompanyService;

import java.util.List;

@Controller
@RequestMapping("/practice-office/companies")
@RequiredArgsConstructor
public class PracticeOfficeCompanyController {

    private final CompanyService companyService;

    @GetMapping()
    public String manageCompanies(Model model) {
        model.addAttribute("companies", companyService.getAllMappedToDto());
        return "practice_office/companies";
    }

    @PostMapping("/delete/{email}")
    public String deleteCompany(@PathVariable String email, RedirectAttributes redirectAttributes) {
        companyService.deleteByEmail(email);
        redirectAttributes.addFlashAttribute("successMessage", "Company deleted successfully.");
        return "redirect:/practice-office/companies";
    }
}
