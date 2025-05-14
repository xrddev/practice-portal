package xrddev.practiceportal.service.impl.periods;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xrddev.practiceportal.model.period.ApplicationPeriod;
import xrddev.practiceportal.model.enums.PeriodStatus;
import xrddev.practiceportal.repository.api.ApplicationPeriodRepository;
import xrddev.practiceportal.service.api.ApplicationPeriodService;

@Service
@RequiredArgsConstructor
public class ApplicationPeriodServiceImpl implements ApplicationPeriodService {

    private final ApplicationPeriodRepository applicationPeriodRepository;

    @Override
    @Transactional(readOnly = true)
    public ApplicationPeriod getPeriod() {
        return applicationPeriodRepository.findById(1)
                .orElseThrow(() -> new IllegalStateException("No application period found"));
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isOpen() {
        return getPeriod().isOpen();
    }

    @Override
    @Transactional
    public void updateStatus(PeriodStatus mode) {
        ApplicationPeriod p = getPeriod();
        p.setStatusMode(mode);
    }
}