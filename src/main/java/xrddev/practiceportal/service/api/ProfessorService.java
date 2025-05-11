package xrddev.practiceportal.service.api;

import xrddev.practiceportal.dto.professor.ProfessorDashboardDto;
import xrddev.practiceportal.model.user.Professor;
import java.util.List;
import java.util.Optional;

public interface ProfessorService {
    void registerProfessor(String email, String password,
                           String firstName, String lastName,
                           List<String> interests);
    Optional<Professor> findByEmail(String email);
    void updateProfessor(ProfessorDashboardDto dto, String email);
    long count();

    ProfessorDashboardDto getByEmailMappedToDto(String email);

}