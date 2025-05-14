package xrddev.practiceportal.service.impl.periods;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xrddev.practiceportal.model.period.EvaluationPeriod;
import xrddev.practiceportal.model.enums.PeriodStatus;
import xrddev.practiceportal.repository.api.EvaluationPeriodRepository;
import xrddev.practiceportal.service.api.EvaluationPeriodService;

@Service
@RequiredArgsConstructor
public class EvaluationPeriodServiceImpl implements EvaluationPeriodService {

    private final EvaluationPeriodRepository evaluationPeriodRepository;

    @Override
    @Transactional(readOnly = true)
    public EvaluationPeriod getPeriod() {
        return evaluationPeriodRepository
                .findById(1)
                .orElseThrow(() -> new IllegalStateException("No evaluation period found"));
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isOpen() {
        return getPeriod().isOpen();
    }

    @Override
    @Transactional
    public void updateStatus(PeriodStatus mode) {
        EvaluationPeriod period = getPeriod();
        period.setStatusMode(mode);
    }
}