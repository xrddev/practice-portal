package xrddev.practiceportal.service.api;

import xrddev.practiceportal.model.period.EvaluationPeriod;
import xrddev.practiceportal.model.enums.PeriodStatus;

public interface EvaluationPeriodService {
    EvaluationPeriod getPeriod();
    boolean isOpen();
    void updateStatus(PeriodStatus mode);
}