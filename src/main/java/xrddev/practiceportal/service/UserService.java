package xrddev.practiceportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xrddev.practiceportal.repository.UserRepository;
import xrddev.practiceportal.model.User;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Αποθήκευση χρήστη στη βάση δεδομένων
    public User registerUser(User user) {
        // Επικύρωση: αν υπάρχει ήδη email ή username
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Email is already taken.");
        }
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new RuntimeException("Username is already taken.");
        }

        // Κρυπτογράφηση του password (προαιρετικά)
        user.setPassword(encryptPassword(user.getPassword()));

        // Αποθήκευση στη βάση δεδομένων
        return userRepository.save(user);
    }

    // Μέθοδος κρυπτογράφησης password (για Spring Security Bcrypt)
    private String encryptPassword(String password) {
        // Στην πραγματική εφαρμογή θα χρησιμοποιήσεις BCryptPasswordEncoder
        return password; // Το αφήνουμε απλό για αρχή
    }
}