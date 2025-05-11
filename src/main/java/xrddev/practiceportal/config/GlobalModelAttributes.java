package xrddev.practiceportal.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import xrddev.practiceportal.model.enums.Department;
import xrddev.practiceportal.model.enums.Interests;
import xrddev.practiceportal.model.enums.Skills;
import xrddev.practiceportal.service.api.ApplicationPeriodService;
import xrddev.practiceportal.service.api.EvaluationPeriodService;

import java.util.Arrays;
import java.util.List;

@ControllerAdvice
public class GlobalModelAttributes {

    private final ApplicationPeriodService applicationPeriodService;
    private final EvaluationPeriodService evaluationPeriodService;

    public GlobalModelAttributes(ApplicationPeriodService applicationPeriodService,
                                 EvaluationPeriodService evaluationPeriodService) {
        this.applicationPeriodService = applicationPeriodService;
        this.evaluationPeriodService = evaluationPeriodService;
    }

    @ModelAttribute("ApplicationsStatus")
    public boolean appOpen() {
        return applicationPeriodService.isOpen();
    }

    @ModelAttribute("EvaluationsStatus")
    public boolean evalOpen() {
        return evaluationPeriodService.isOpen();
    }

    @ModelAttribute("SKILLS")
    public List<String> skills() {
        return Arrays.stream(Skills.values())
                .map(Enum::name)
                .toList();
    }

    @ModelAttribute("INTERESTS")
    public List<String> interests() {
        return Arrays.stream(Interests.values())
                .map(Enum::name)
                .toList();
    }

    @ModelAttribute("DEPARTMENTS")
    public List<String> departments() {
        return Arrays.stream(Department.values())
                .map(Enum::name)
                .toList();
    }
}
