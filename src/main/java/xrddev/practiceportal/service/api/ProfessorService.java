package xrddev.practiceportal.service.api;

import xrddev.practiceportal.dto.user.professor.ProfessorDto;
import xrddev.practiceportal.dto.user.professor.ProfessorRegistrationDto;
import xrddev.practiceportal.model.user.Professor;
import java.util.List;
import java.util.Optional;

public interface ProfessorService {
    public void registerProfessor(ProfessorRegistrationDto professorRegistrationDto);

    Optional<Professor> findByEmail(String email);
    void updateProfessor(ProfessorDto dto, String email);
    long count();

    ProfessorDto getByEmailMappedToDto(String email);
    List<ProfessorDto> getAllMappedToDto();
    void deleteById(Long id);

}