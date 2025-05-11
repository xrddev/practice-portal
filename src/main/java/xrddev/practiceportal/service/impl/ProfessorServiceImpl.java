package xrddev.practiceportal.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xrddev.practiceportal.dto.professor.ProfessorDashboardDto;
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
                                  List<String> interests) {
        Professor professor = new Professor();
        professor.setRole(UserRole.PROFESSOR);
        professor.setEmail(email);
        professor.setPassword(password);
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
    public void updateProfessor(ProfessorDashboardDto dto, String email) {
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

}