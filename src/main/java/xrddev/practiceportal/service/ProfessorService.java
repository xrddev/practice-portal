package xrddev.practiceportal.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import xrddev.practiceportal.model.Professor;
import xrddev.practiceportal.model.enums.Interests;
import xrddev.practiceportal.repository.ProfessorRepository;

import java.util.List;

@Service
public class ProfessorService {
    private final ProfessorRepository professorRepository;

    public ProfessorService(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    @Transactional
    public void registerProfessor(String email, String password, String firstName, String lastName, List<String> interests){
        Professor professor = new Professor();
        professor.setEmail(email);
        professor.setPassword(password);
        professor.setFirstName(firstName);
        professor.setLastName(lastName);
        professor.setInterests(interests.stream().map(Interests::valueOf).toList());
        professorRepository.save(professor);
    }

}
