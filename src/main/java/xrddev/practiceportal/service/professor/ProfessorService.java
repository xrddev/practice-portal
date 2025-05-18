package xrddev.practiceportal.service.professor;

import xrddev.practiceportal.dto.professor.ProfessorDashboardDto;
import xrddev.practiceportal.dto.professor.ProfessorEditDto;
import xrddev.practiceportal.dto.professor.ProfessorRegistrationDto;
import xrddev.practiceportal.model.professor.Professor;

import java.util.List;
import java.util.Optional;

public interface ProfessorService {
    public void registerProfessor(ProfessorRegistrationDto professorRegistrationDto);

    Optional<Professor> findByEmail(String email);
    void updateProfessor(ProfessorEditDto dto, String email);
    long count();

    ProfessorEditDto getByEmailMappedToEditDto(String email);

    ProfessorDashboardDto getByEmailMappedToDashboardDto(String email);

    void deleteById(Long id);

    List<ProfessorDashboardDto> getAllMappedToDashboardDto();

    List<Professor> getAll();
}