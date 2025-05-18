package xrddev.practiceportal.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import xrddev.practiceportal.dto.practice_office.PracticeOfficeAdminDto;
import xrddev.practiceportal.service.practice_office.PracticeOfficeAdminService;

import java.util.Arrays;
import java.util.List;

@Configuration
public class AdminDataInitializer {

    @Bean
    @Order(100)
    ApplicationRunner seedAdmins(
            PracticeOfficeAdminService adminService,
            PasswordEncoder passwordEncoder,
            @Value("${app.admin.emails}") String emailsCsv,
            @Value("${app.admin.passwords}") String pwCsv)
    {
        List<String> emails = Arrays.stream(emailsCsv.split(","))
                                    .map(String::trim)
                                    .toList();
        List<String> pws    = Arrays.stream(pwCsv.split(","))
                                    .map(String::trim)
                                    .toList();

        if (emails.size() != pws.size()) {
            throw new IllegalArgumentException(
                "app.admin.emails και app.admin.passwords πρέπει να έχουν ίδιο πλήθος στοιχείων");
        }

        return args -> {
            for (int i = 0; i < emails.size(); i++) {
                String email = emails.get(i);
                String rawPw = pws.get(i);

                if (adminService.findByEmail(email).isEmpty()) {
                    PracticeOfficeAdminDto dto = new PracticeOfficeAdminDto();
                    dto.setEmail(email);
                    dto.setPassword(passwordEncoder.encode(rawPw));
                    adminService.registerAdmin(dto);
                    System.out.printf(">> Created admin %s with pw from props%n", email);
                }
            }
        };
    }
}