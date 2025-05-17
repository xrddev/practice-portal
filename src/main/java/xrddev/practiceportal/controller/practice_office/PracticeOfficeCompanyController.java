package xrddev.practiceportal.controller.practice_office;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import xrddev.practiceportal.service.company.CompanyService;
import xrddev.practiceportal.service.internship.InternshipPositionService;

@Controller
@RequestMapping("/practice-office/companies")
@RequiredArgsConstructor
public class PracticeOfficeCompanyController {

    private final CompanyService companyService;
    private final InternshipPositionService internshipPositionService;

    @GetMapping("/dashboard")
    public String manageCompanies(Model model) {
        model.addAttribute("companies", companyService.getAllMappedToDashboardDto());
        return "/practice_office/companies/dashboard";
    }

    @PostMapping("/delete/{id}")
    public String deleteCompany(@PathVariable Long id) {
        companyService.deleteById(id);
        return "redirect:/practice-office/companies/dashboard";
    }

    @GetMapping("/{id}/positions")
    public String showCompanyPositions(@PathVariable Long id, Model model) {
        model.addAttribute("companyId", id);
        model.addAttribute("companyName", companyService.getById(id).getCompanyName());
        model.addAttribute("positions", internshipPositionService.getAllByCompanyIdMappedToDashboardDto(id));
        return "/practice_office/companies/company_positions";
    }

    @PostMapping("/positions/delete/{positionId}/{companyId}")
    public String deletePosition(@PathVariable Long positionId, @PathVariable Long companyId) {
        internshipPositionService.deleteById(positionId);
        return "redirect:/practice-office/companies/" + companyId + "/positions";
    }
}
