package xrddev.practiceportal.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xrddev.practiceportal.dto.professor.ProfessorDto;
import xrddev.practiceportal.model.enums.Department;
import xrddev.practiceportal.model.user.Professor;
import xrddev.practiceportal.model.enums.Interests;
import xrddev.practiceportal.model.enums.UserRole;
import xrddev.practiceportal.repository.api.ProfessorRepository;
import xrddev.practiceportal.service.api.ProfessorService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ProfessorServiceImpl implements ProfessorService {

    private final ProfessorRepository professorRepository;

    @Override
    @Transactional
    public void registerProfessor(String email,
                                  String password,
                                  String firstName,
                                  String lastName,
                                  Department department,
                                  List<String> interests) {
        Professor professor = new Professor();
        professor.setRole(UserRole.PROFESSOR);
        professor.setEmail(email);
        professor.setPassword(password);
        professor.setDepartment(department);
        professor.setFirstName(firstName);
        professor.setLastName(lastName);
        professor.setInterests(
            interests.stream()
                     .map(Interests::valueOf)
                     .toList()
        );
        professorRepository.save(professor);
    }

    @Override
    @Transactional
    public Optional<Professor> findByEmail(String email) {
        return professorRepository.findByEmail(email);
    }

    @Override
    @Transactional
    public void updateProfessor(ProfessorDto dto, String email) {
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
    public ProfessorDto getByEmailMappedToDto(String email) {
        return professorRepository.findByEmail(email)
                .map(ProfessorDto::new)
                .orElseThrow(() -> new EntityNotFoundException("Professor not found"));
    }

    @Override
    public List<ProfessorDto> getAllMappedToDto() {
        return professorRepository.findAll()
                .stream()
                .map(ProfessorDto::new)
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        professorRepository.deleteById(id);
    }

}