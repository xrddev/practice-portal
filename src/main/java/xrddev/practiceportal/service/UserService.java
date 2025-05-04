package xrddev.practiceportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xrddev.practiceportal.repository.UserRepository;
import xrddev.practiceportal.model.User;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Υλοποίηση του existsByUsername
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    // Υλοποίηση αποθήκευσης χρήστη
    public void save(User user) {
        userRepository.save(user);
    }
}