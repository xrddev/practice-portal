package xrddev.practiceportal.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xrddev.practiceportal.dto.user.professor.ProfessorDashboardDto;
import xrddev.practiceportal.dto.user.professor.ProfessorEditDto;
import xrddev.practiceportal.dto.user.professor.ProfessorRegistrationDto;
import xrddev.practiceportal.model.enums.UserRole;
import xrddev.practiceportal.model.user.Professor;
import xrddev.practiceportal.repository.api.ProfessorRepository;
import xrddev.practiceportal.service.api.ProfessorService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ProfessorServiceImpl implements ProfessorService {

    private final ProfessorRepository professorRepository;

    public void registerProfessor(ProfessorRegistrationDto professorRegistrationDto) {
        Professor professor = new Professor();

        professor.setEmail(professorRegistrationDto.getEmail());
        professor.setPassword(professorRegistrationDto.getPassword());
        professor.setRole(UserRole.PROFESSOR);

        professor.setFirstName(professorRegistrationDto.getFirstName());
        professor.setLastName(professorRegistrationDto.getLastName());
        professor.setDepartment(professorRegistrationDto.getDepartment());
        professor.setInterests(professorRegistrationDto.getInterests());

        professorRepository.save(professor);
    }

    @Override
    @Transactional
    public Optional<Professor> findByEmail(String email) {
        return professorRepository.findByEmail(email);
    }

    @Override
    @Transactional
    public void updateProfessor(ProfessorEditDto dto, String email) {
        Professor professor = professorRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Professor not found"));

        professor.setFirstName(dto.getFirstName());
        professor.setLastName(dto.getLastName());
        professor.setInterests(dto.getInterests());
        professorRepository.save(professor);
    }

    @Override
    public long count() {
        return professorRepository.count();
    }

    @Override
    public ProfessorEditDto getByEmailMappedToEditDto(String email) {
        return professorRepository.findByEmail(email)
                .map(ProfessorEditDto::new)
                .orElseThrow(() -> new EntityNotFoundException("Professor not found"));
    }

    @Override
    public ProfessorDashboardDto getByEmailMappedToDashboardDto(String email) {
        return professorRepository.findByEmail(email)
                .map(ProfessorDashboardDto::new)
                .orElseThrow(() -> new EntityNotFoundException("Professor not found"));
    }


    @Override
    public void deleteById(Long id) {
        professorRepository.deleteById(id);
    }


    @Override
    public List<ProfessorDashboardDto> getAllMappedToDashboardDto() {
        return professorRepository.findAll()
                .stream()
                .map(ProfessorDashboardDto::new)
                .toList();
    }
}