package xrddev.practiceportal.service.periods;

import xrddev.practiceportal.model.period.ApplicationPeriod;
import xrddev.practiceportal.model.enums.PeriodStatus;

public interface ApplicationPeriodService {
    ApplicationPeriod getPeriod();
    boolean isOpen();
    void updateStatus(PeriodStatus mode);
}