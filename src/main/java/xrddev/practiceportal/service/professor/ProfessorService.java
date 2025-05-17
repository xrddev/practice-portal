package xrddev.practiceportal.service.professor;

import xrddev.practiceportal.dto.user.professor.ProfessorDashboardDto;
import xrddev.practiceportal.dto.user.professor.ProfessorEditDto;
import xrddev.practiceportal.dto.user.professor.ProfessorRegistrationDto;
import xrddev.practiceportal.model.user.Professor;

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