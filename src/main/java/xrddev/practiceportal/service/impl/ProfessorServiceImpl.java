package xrddev.practiceportal.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
}