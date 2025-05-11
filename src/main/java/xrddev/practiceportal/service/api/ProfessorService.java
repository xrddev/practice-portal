package xrddev.practiceportal.service.api;

import xrddev.practiceportal.dto.professor.ProfessorDto;
import xrddev.practiceportal.model.enums.Department;
import xrddev.practiceportal.model.user.Professor;
import java.util.List;
import java.util.Optional;

public interface ProfessorService {
    void registerProfessor(String email, String password,
                           String firstName, String lastName,
                           Department department,
                           List<String> interests);
    Optional<Professor> findByEmail(String email);
    void updateProfessor(ProfessorDto dto, String email);
    long count();

    ProfessorDto getByEmailMappedToDto(String email);
    List<ProfessorDto> getAllMappedToDto();
    void deleteById(Long id);

}