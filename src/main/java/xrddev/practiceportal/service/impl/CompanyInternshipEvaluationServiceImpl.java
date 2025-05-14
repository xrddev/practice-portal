package xrddev.practiceportal.service.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xrddev.practiceportal.dto.intership_position.CompanyInternshipEvaluationDto;
import xrddev.practiceportal.model.internship.CompanyEvaluation;
import xrddev.practiceportal.model.internship.InternshipPosition;
import xrddev.practiceportal.repository.api.CompanyEvaluationRepository;
import xrddev.practiceportal.repository.api.InternshipPositionRepository;
import xrddev.practiceportal.service.api.CompanyInternshipEvaluationService;

@Service
@RequiredArgsConstructor
public class CompanyInternshipEvaluationServiceImpl implements CompanyInternshipEvaluationService {

    private final InternshipPositionRepository internshipPositionRepository;
    private final CompanyEvaluationRepository companyInternshipEvaluationRepository;

    @Override
    @Transactional
    public void evaluate(CompanyInternshipEvaluationDto dto) {
        InternshipPosition position = internshipPositionRepository.findById(dto.getPositionId())
                .orElseThrow(() -> new EntityNotFoundException("Internship position not found"));

        CompanyEvaluation evaluation = new CompanyEvaluation();
        evaluation.setMotivation(dto.getMotivation());
        evaluation.setEffectiveness(dto.getEffectiveness());
        evaluation.setEfficiency(dto.getEfficiency());
        evaluation.setOverallGrade(dto.getOverallGrade());
        evaluation.setComments(dto.getComments());
        evaluation.setInternshipPosition(position);

        companyInternshipEvaluationRepository.save(evaluation);
        position.setCompanyInternshipEvaluation(evaluation);
        internshipPositionRepository.save(position);
    }
}
