package xrddev.practiceportal.advice;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import xrddev.practiceportal.service.api.ApplicationPeriodService;
import xrddev.practiceportal.service.api.EvaluationPeriodService;

@ControllerAdvice
public class PeriodAdvice {

    private final ApplicationPeriodService applicationPeriodService;
    private final EvaluationPeriodService evaluationPeriodService;

    public PeriodAdvice(ApplicationPeriodService applicationPeriodService,
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
}
