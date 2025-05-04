package xrddev.practiceportal.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import xrddev.practiceportal.model.PracticeOfficeAdministrator;
import xrddev.practiceportal.model.enums.Department;
import xrddev.practiceportal.service.UserService;

import java.util.List;
import java.util.Set;

@Component
public class PracticeAdminInitializer implements CommandLineRunner {

    private final UserService userService;

    public PracticeAdminInitializer(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) {

        // Δημιουργία των administrators με set methods
        PracticeOfficeAdministrator adminStem = new PracticeOfficeAdministrator();
        adminStem.setUsername("ADMIN_STEM");
        adminStem.setPassword(encodePassword("q4Yt!9eP@7v#ZrF2"));
        adminStem.setEmail("practice-office-stem@uoi.gr");
        adminStem.setAssignedDepartments(Set.of(
                Department.COMPUTER_SCIENCE_AND_ENGINEERING,
                Department.MATHEMATICS,
                Department.PHYSICS,
                Department.CHEMISTRY,
                Department.BIOLOGY,
                Department.MATERIALS_SCIENCE,
                Department.INFORMATICS_TELECOMMUNICATIONS,
                Department.AGRICULTURE
        ));

        PracticeOfficeAdministrator adminHealth = new PracticeOfficeAdministrator();
        adminHealth.setUsername("ADMIN_HEALTH");
        adminHealth.setPassword(encodePassword("Lp@3zX#2Ve!8mQaR"));
        adminHealth.setEmail("practice-office-health@uoi.gr");
        adminHealth.setAssignedDepartments(Set.of(
                Department.MEDICINE,
                Department.NURSING,
                Department.BIOLOGICAL_APPLICATIONS,
                Department.SPEECH_LANGUAGE_THERAPY
        ));

        PracticeOfficeAdministrator adminArts = new PracticeOfficeAdministrator();
        adminArts.setUsername("ADMIN_ARTS");
        adminArts.setPassword(encodePassword("Z!8vG$e2LpX@4qRt"));
        adminArts.setEmail("practice-office-arts@uoi.gr");
        adminArts.setAssignedDepartments(Set.of(
                Department.LITERATURE,
                Department.HISTORY,
                Department.PHILOSOPHY,
                Department.FINE_ARTS,
                Department.MUSIC_STUDIES,
                Department.TRANSLATION_INTERPRETING
        ));

        PracticeOfficeAdministrator adminSocial = new PracticeOfficeAdministrator();
        adminSocial.setUsername("ADMIN_SOCIAL");
        adminSocial.setPassword(encodePassword("Xr@9pL#vQ7!8eMzY"));
        adminSocial.setEmail("practice-office-social@uoi.gr");
        adminSocial.setAssignedDepartments(Set.of(
                Department.ECONOMICS,
                Department.ACCOUNTING_FINANCE,
                Department.PSYCHOLOGY,
                Department.PRIMARY_EDUCATION,
                Department.EARLY_CHILDHOOD_EDUCATION,
                Department.EARLY_YEARS_LEARNING_CARE,
                Department.ARCHITECTURE
        ));

        List<PracticeOfficeAdministrator> adminList = List.of(adminStem, adminHealth, adminArts, adminSocial);

        // Αποθήκευση μόνο αν δεν υπάρχει
        adminList.forEach(admin -> {
            if (!userService.existsByUsername(admin.getUsername())) {
                userService.save(admin);
                System.out.println("✅ PracticeOfficeAdministrator '" + admin.getUsername() + "' δημιουργήθηκε.");
            } else {
                System.out.println("ℹ️ PracticeOfficeAdministrator '" + admin.getUsername() + "' υπάρχει ήδη.");
            }
        });
    }

    private String encodePassword(String rawPassword) {
        return new BCryptPasswordEncoder().encode(rawPassword);
    }
}
